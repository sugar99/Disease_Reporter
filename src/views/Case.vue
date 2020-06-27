<template>
    <div>
        <div style="display: flex;justify-content: space-between">
            <div>
                <el-input placeholder="请输入疾病id搜索..." prefix-icon="el-icon-search"
                          style="width: 350px;margin-right: 10px"
                          clearable
                          @clear="initCase"
                          v-model="disease_id"
                          @keydown.enter.native="initCase">
                </el-input>
                <el-button icon="el-icon-search" type="primary" @click="initCase">搜索</el-button>
            </div>
            <el-button icon="el-icon-add" type="primary" @click="addCase" style="margin-right: 10px">新增</el-button>
        </div>
        <div style="margin-top: 10px">
            <el-table
                    :data="cases"
                    stripe
                    border>
                <el-table-column
                        prop="disease_id"
                        label="疾病id"
                        width="160">
                </el-table-column>
                <el-table-column
                        prop="patient_id"
                        label="病人id"
                        width="130">
                </el-table-column>
                <el-table-column
                        prop="place"
                        label="地点">
                </el-table-column>
                <el-table-column
                        prop="date"
                        label="日期"
                        width="140">
                </el-table-column>
                <el-table-column
                        fixed="right"
                        width="140"
                        label="操作">
                    <template slot-scope="scope">
                        <el-button @click="editCase(scope.row)" style="padding: 3px" type="primary">编辑</el-button>
                        <el-button @click="deleteCase(scope.row)" style="padding: 3px" type="danger">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <div style="display: flex;justify-content: flex-end">
            <el-pagination
                    background
                    @current-change="currentChange"
                    @size-change="sizeChange"
                    layout="sizes, prev, pager, next, jumper, ->, total, slot"
                    :total="total">
            </el-pagination>
        </div>
        <el-dialog :title="dialogTitle" :visible.sync="dialogVisible" width="60%">
            <div>
                <el-form :model="cas" :rules="rules" ref="diseaseForm">
                    <el-form-item label="疾病id:" prop="dname">
                        <el-input style="width: 150px" prefix-icon="el-icon-edit" v-model="cas.disease_id"
                                  placeholder="请输入疾病id"/>
                    </el-form-item>
                    <el-form-item label="病人id:" prop="nick_name">
                        <el-input style="width: 150px" prefix-icon="el-icon-edit" v-model="cas.patient_id"
                                  placeholder="请输入病人id"/>
                    </el-form-item>
                    <el-form-item label="日期:" prop="date">
                        <el-date-picker
                                v-model="cas.date"
                                type="date"
                                value-format="yyyy-MM-dd"
                                placeholder="选择日期">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item label="地点:" prop="place">
                        <el-input style="width: 200px" prefix-icon="el-icon-edit" v-model="cas.place"
                                  placeholder="请输入地点"/>
                    </el-form-item>
                </el-form>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="onConfirmDialog">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "Case",
        data() {
            return {
                cases: [
                    {
                        case_id: "01",
                        disease_id: "01",
                        date: "2020-02-02",
                        place: "广州市瑶台村",
                        patient_id: "07"
                    },
                    {
                        case_id: "02",
                        disease_id: "01",
                        date: "2020-02-02",
                        place: "广州市瑶台村",
                        patient_id: "07"
                    }
                ],
                total: 0,
                page: 1,
                size: 10,
                disease_id:'',
                dialogTitle: "编辑病人",
                dialogVisible: false,
                update: false,
                cas: {
                    disease_id: "",
                    date: "",
                    place: "",
                    patient_id: ""
                },
                rules: {
                    disease_id: [{required: true, message: '请输入疾病id', trigger: 'blur'}],
                    date: [{required: true, message: '请选择日期', trigger: 'blur'}],
                    place: [{required: true, message: '请输入地点', trigger: 'blur'}],
                    patient_id: [{required: true, message: '请输入病人id', trigger: 'blur'}]
                }
            }
        },
        mounted() {
            this.initCase();
        },
        methods: {
            initCase(){
                this.getRequest("http://127.0.0.1:8300/case/1").then(resp => {
                    if (resp) {
                        this.dialogVisible = false;
                        console.log(resp.data)
                    }
                })
            },
            editCase(data){
                this.dialogVisible=true
                this.dialogTitle="编辑病例"
                this.cas=data
                this.update=true
            },
            addCase(){
                this.dialogVisible=true
                this.dialogTitle="添加病例"
                this.cas={
                    disease_id: "",
                    date: "",
                    place: "",
                    patient_id: ""
                }
                this.update=false
            },
            currentChange(currentPage){
                this.page = currentPage;
                this.initCase();
            },
            sizeChange(currentSize){
                this.size = currentSize;
                this.initCase();
            },
            onConfirmDialog(){
                console.log(this.cas)
                this.$refs['diseaseForm'].validate((valid) => {
                    if (valid) {
                        if(this.update){
                            this.postRequest("http://127.0.0.1:8300/case/update",this.cas).then(resp => {
                                if (resp) {
                                    this.dialogVisible = false;
                                    this.initCase();
                                }
                            })
                        }else{
                            this.postRequest("/case/add",this.cas).then(resp => {
                                if (resp) {
                                    this.dialogVisible = false;
                                    this.initCase();
                                }
                            })
                        }
                    } else {
                        this.$message.error('请输入所有字段');
                        return false;
                    }
                });
            },
            deleteCase(data){
                this.$confirm('将删除该病例, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.getRequest("http://127.0.0.1:8300/case/delete/" + data.case_id).then(resp => {
                        if (resp) {
                            this.initCase();
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消'
                    });
                });
            }
        }
    }
</script>

<style scoped>

</style>
