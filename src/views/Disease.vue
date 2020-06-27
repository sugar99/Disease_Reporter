<template>
    <div>
        <div style="display: flex;justify-content: space-between">
            <div>
                <el-input placeholder="请输入疾病id搜索..." prefix-icon="el-icon-search"
                          style="width: 350px;margin-right: 10px"
                          clearable
                          @clear="initDisease"
                          v-model="dname"
                          @keydown.enter.native="initDisease">
                </el-input>
                <el-button icon="el-icon-search" type="primary" @click="initDisease">搜索</el-button>
            </div>
            <el-button icon="el-icon-add" type="primary" @click="addDisease" style="margin-right: 10px">新增</el-button>
        </div>
        <div style="margin-top: 10px">
            <el-table
                    :data="diseases"
                    stripe
                    border>
                <el-table-column
                        prop="dname"
                        label="名字"
                        width="160">
                </el-table-column>
                <el-table-column
                        prop="nick_name"
                        label="别名"
                        width="130">
                </el-table-column>
                <el-table-column
                        prop="level"
                        label="等级"
                        width="80">
                </el-table-column>
                <el-table-column
                        prop="date"
                        label="日期"
                        width="140">
                </el-table-column>
                <el-table-column
                        prop="place"
                        label="地点"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="sympyom"
                        label="症状">
                </el-table-column>
                <el-table-column
                        prop="cure"
                        label="药物"
                        width="140">
                </el-table-column>
                <el-table-column
                        fixed="right"
                        width="140"
                        label="操作">
                    <template slot-scope="scope">
                        <el-button @click="editDisease(scope.row)" style="padding: 3px" type="primary">编辑</el-button>
                        <el-button @click="deleteDisease(scope.row)" style="padding: 3px" type="danger">删除</el-button>
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
                <el-form :model="disease" :rules="rules" ref="diseaseForm">
                    <el-form-item label="名字:" prop="dname">
                        <el-input style="width: 150px" prefix-icon="el-icon-edit" v-model="disease.dname"
                                  placeholder="请输入名字"/>
                    </el-form-item>
                    <el-form-item label="别名:" prop="nick_name">
                        <el-input style="width: 150px" prefix-icon="el-icon-edit" v-model="disease.nick_name"
                                  placeholder="请输入别名"/>
                    </el-form-item>
                    <el-form-item label="日期:" prop="date">
                        <el-date-picker
                                v-model="disease.date"
                                type="date"
                                value-format="yyyy-MM-dd"
                                placeholder="选择日期">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item label="等级:" prop="level">
                        <el-input style="width: 150px" prefix-icon="el-icon-edit" v-model="disease.level"
                                  placeholder="请输入等级"/>
                    </el-form-item>
                    <el-form-item label="地点:" prop="place">
                        <el-input style="width: 200px" prefix-icon="el-icon-edit" v-model="disease.place"
                                  placeholder="请输入地点"/>
                    </el-form-item>
                    <el-form-item label="症状:" prop="sympyom">
                        <el-input style="width: 200px" prefix-icon="el-icon-edit" v-model="disease.sympyom"
                                  placeholder="请输入症状"/>
                    </el-form-item>
                    <el-form-item label="药物:" prop="cure">
                        <el-input style="width: 200px" prefix-icon="el-icon-edit" v-model="disease.cure"
                                  placeholder="请输入药物"/>
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
        name: "Manage",
        data() {
            return {
                diseases: [
                    {
                        disease_id: "01",
                        dname: "新型冠状病毒感染的肺炎",
                        nick_name: "新冠肺炎",
                        level: 3,
                        date: "2020-01-20",
                        place: "武汉",
                        sympyom: "高烧呕吐xxxxx",
                        cure: "特效药xxx"
                    },
                    {
                        disease_id: "02",
                        dname: "新型冠状病毒感染的肺炎",
                        nick_name: "新冠肺炎",
                        level: 3,
                        date: "2020-01-20",
                        place: "武汉",
                        sympyom: "高烧呕吐xxxxx",
                        cure: "特效药xxx"
                    }
                ],
                total: 0,
                page: 1,
                size: 10,
                dname:'',
                dialogTitle: "编辑病人",
                dialogVisible: false,
                update: false,
                disease: {
                    dname: "",
                    nick_name: "",
                    level: 3,
                    date: "2020-01-20",
                    place: "",
                    sympyom: "",
                    cure: ""
                },
                rules: {
                    dname: [{required: true, message: '请输入名字', trigger: 'blur'}],
                    nick_name: [{required: true, message: '请选择别名', trigger: 'blur'}],
                    date: [{required: true, message: '请输入日期', trigger: 'blur'}],
                    level: [{required: true, message: '请输入等级', trigger: 'blur'}],
                    place: [{required: true, message: '请输入地点', trigger: 'blur'}],
                    sympyom: [{required: true, message: '请输入症状', trigger: 'blur'}],
                    cure: [{required: true, message: '请输入药物', trigger: 'blur'}]
                }
            }
        },
        mounted() {
            this.initDisease();
        },
        methods: {
            initDisease(){
                this.getRequest("http://127.0.0.1:8200/disease/1").then(resp => {
                    if (resp) {
                        this.dialogVisible = false;
                        console.log(resp.data)
                    }
                })
            },
            editDisease(data){
                this.dialogVisible=true
                this.dialogTitle="编辑疾病"
                this.disease=data
                this.update=true
            },
            addDisease(){
                this.dialogVisible=true
                this.dialogTitle="添加疾病"
                this.disease={
                    dname: "",
                    nick_name: "",
                    level: 3,
                    date: "2020-01-20",
                    place: "",
                    sympyom: "",
                    cure: ""
                }
                this.update=false
            },
            currentChange(currentPage){
                this.page = currentPage;
                this.initDisease();
            },
            sizeChange(currentSize){
                this.size = currentSize;
                this.initDisease();
            },
            onConfirmDialog(){
                this.$refs['diseaseForm'].validate((valid) => {
                    if (valid) {
                        if(this.update){
                            this.postRequest("http://127.0.0.1:8200/disease/update",this.disease).then(resp => {
                                if (resp) {
                                    this.dialogVisible = false;
                                    this.initDisease();
                                }
                            })
                        }else{
                            this.postRequest("/disease/add",this.disease).then(resp => {
                                if (resp) {
                                    this.dialogVisible = false;
                                    this.initDisease();
                                }
                            })
                        }
                    } else {
                        this.$message.error('请输入所有字段');
                        return false;
                    }
                });
            },
            deleteDisease(data){
                this.$confirm('将删除该疾病, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.getRequest("http://127.0.0.1:8200/disease/delete/" + data.disease_id).then(resp => {
                        if (resp) {
                            this.initDisease();
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
