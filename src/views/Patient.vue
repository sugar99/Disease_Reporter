<template>
    <div>
        <div style="display: flex;justify-content: space-between">
            <div>
                <el-input placeholder="请输入名字关键字搜索..." prefix-icon="el-icon-search"
                          style="width: 350px;margin-right: 10px"
                          clearable
                          @clear="initPatients"
                          v-model="pname"
                          @keydown.enter.native="initPatients">
                </el-input>
                <el-button icon="el-icon-search" type="primary" @click="initPatients">搜索</el-button>
            </div>
            <el-button icon="el-icon-add" type="primary" @click="addPatient" style="margin-right: 10px">新增</el-button>
        </div>
        <div style="margin-top: 10px">
            <el-table
                    :data="patients"
                    stripe
                    border>
                <el-table-column
                        prop="pname"
                        label="姓名"
                        width="130">
                </el-table-column>
                <el-table-column
                        prop="sex"
                        label="性别"
                        width="100">
                    <template slot-scope="scope">
                        <p>{{scope.row.sex==1?"男":"女"}}</p>
                    </template>
                </el-table-column>
                <el-table-column
                        prop="age"
                        label="年龄"
                        width="100">
                </el-table-column>
                <el-table-column
                        prop="address"
                        label="住址">
                </el-table-column>
                <el-table-column
                        prop="id_card_number"
                        label="证件号码"
                        width="180">
                </el-table-column>
                <el-table-column
                        fixed="right"
                        width="140"
                        label="操作">
                    <template slot-scope="scope">
                        <el-button @click="editPatient(scope.row)" style="padding: 3px" type="primary">编辑</el-button>
                        <el-button @click="deletePatient(scope.row)" style="padding: 3px" type="danger">删除</el-button>
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
                <el-form :model="patient" :rules="rules" ref="patientForm">
                    <el-form-item label="姓名:" prop="pname">
                        <el-input style="width: 150px" prefix-icon="el-icon-edit" v-model="patient.pname"
                                  placeholder="请输入姓名"/>
                    </el-form-item>
                    <el-form-item label="性别" prop="sex">
                        <el-radio-group v-model="patient.sex">
                            <el-radio label="2">女</el-radio>
                            <el-radio label="1">男</el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <el-form-item label="年龄:" prop="age">
                        <el-input style="width: 150px" prefix-icon="el-icon-edit" v-model="patient.age"
                                  placeholder="请输入年龄"/>
                    </el-form-item>
                    <el-form-item label="地址:" prop="address">
                        <el-input style="width: 200px" prefix-icon="el-icon-edit" v-model="patient.address"
                                  placeholder="请输入地址"/>
                    </el-form-item>
                    <el-form-item label="ID卡号:" prop="id_card_number">
                        <el-input style="width: 200px" prefix-icon="el-icon-edit" v-model="patient.id_card_number"
                                  placeholder="请输入ID卡号"/>
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
        name: "Statistics",
        data() {
            return {
                patients: [
                    {
                        patient_id: 7,
                        pname: "李四",
                        sex: "1",
                        age: 21,
                        address: "广州市番禺区xx小区",
                        id_card_number: "510123456789000000"
                    },
                    {
                        patient_id: 8,
                        pname: "王五",
                        sex: "2",
                        age: 21,
                        address: "广州市番禺区xx小区",
                        id_card_number: "510123456789000000"
                    }
                ],
                total: 0,
                page: 1,
                size: 10,
                pname:'',
                dialogTitle: "编辑病人",
                dialogVisible: false,
                update: false,
                patient: {
                    pname: "",
                    sex: "1",
                    age: 0,
                    address: "",
                    id_card_number: ""
                },
                rules: {
                    pname: [{required: true, message: '请输入姓名', trigger: 'blur'}],
                    sex: [{required: true, message: '请选择性别', trigger: 'blur'}],
                    age: [{required: true, message: '请输入年龄', trigger: 'blur'}],
                    address: [{required: true, message: '请输入地址', trigger: 'blur'}],
                    id_card_number: [{required: true, message: '请输入ID卡号', trigger: 'blur'}]
                }
            }
        },
        mounted() {
            this.initPatients();
        },
        methods: {
            initPatients(){
                this.getRequest("http://127.0.0.1:8300/patient/query?size=" + this.size + "&page=" + this.page+"&pname="+this.dname).then(resp => {
                    if (resp) {
                        this.dialogVisible = false;
                        console.log(resp.data)
                    }
                })
            },
            editPatient(data){
                this.dialogVisible=true
                this.dialogTitle="编辑病人"
                this.patient=data
                this.update=true
            },
            addPatient(){
                this.dialogVisible=true
                this.dialogTitle="添加病人"
                this.patient={
                    pname: "",
                    sex: "1",
                    age: 0,
                    address: "",
                    id_card_number: ""
                }
                this.update=false
            },
            currentChange(currentPage){
                this.page = currentPage;
                this.initPatients();
            },
            sizeChange(currentSize){
                this.size = currentSize;
                this.initPatients();
            },
            onConfirmDialog(){
                this.$refs['patientForm'].validate((valid) => {
                    if (valid) {
                        if(this.update){
                            this.postRequest("http://127.0.0.1:8300/patient/update",this.patient).then(resp => {
                                if (resp) {
                                    this.dialogVisible = false;
                                    this.initPatients();
                                }
                            })
                        }else{
                            this.postRequest("/patient/add",this.patient).then(resp => {
                                if (resp) {
                                    this.dialogVisible = false;
                                    this.initPatients();
                                }
                            })
                        }
                    } else {
                        this.$message.error('请输入所有字段');
                        return false;
                    }
                });
            },
            deletePatient(data){
                this.$confirm('将删除该病人, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.getRequest("http://127.0.0.1:8300/patient/delete/" + data.patient_id).then(resp => {
                        if (resp) {
                            this.initPatients();
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
