<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="服务名称" prop="stname">
        <el-input
          v-model="queryParams.stname"
          placeholder="请输入服务名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收费方式" prop="stmtp">
        <el-select v-model="queryParams.stmtp" placeholder="请选择收费方式" clearable>
          <el-option
            v-for="dict in dict.type.sys_shoufei"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="店铺" prop="mid">
        <el-select v-model="queryParams.mid" placeholder="请选择店铺" clearable filterable>
          <el-option
            v-for="dict in options1"
            :key="dict.mid"
            :label="dict.msname"
            :value="dict.mid"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button><br>
      </el-form-item>
      <el-form-item>        
        <i class="el-icon-warning-outline">请先选择店铺，点击查询再进行其他操作</i>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="cum_servicetpList" @selection-change="handleSelectionChange">
      <el-table-column label="服务名称" align="center" prop="stname" />
      <el-table-column label="服务价格" align="center" prop="stmoney" />
      <el-table-column label="收费方式" align="center" prop="stmtp">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_shoufei" :value="scope.row.stmtp"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:cum_servicetp:edit']"
          >查看</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="bookNew(scope.row)"
            v-hasPermi="['system:cum_servicetp:remove']"
          >预约</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改服务种类对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="服务名称" prop="stname">
          <el-input v-model="form.stname" placeholder="请输入服务名称" :disabled="true"/>
        </el-form-item>
        <el-form-item label="服务价格" prop="stmoney">
          <el-input v-model="form.stmoney" placeholder="请输入服务价格" :disabled="true"/>
        </el-form-item>
        <el-form-item label="收费方式" prop="stmtp">
          <el-select v-model="form.stmtp" placeholder="请选择收费方式" :disabled="true">
            <el-option
              v-for="dict in dict.type.sys_shoufei"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="服务描述" prop="stps">
          <el-input v-model="form.stps" placeholder="请输入服务描述" type="textarea" :disabled="true"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 预约 -->
    <el-dialog :title="title" :visible.sync="book" width="500px" append-to-body>
      <el-form ref="form" :model="newForm" :rules="rules" label-width="80px">
        <el-form-item label="宠物" prop="pid">
          <el-select v-model="newForm.pid" placeholder="请选择收费方式" clearable>
            <el-option
              v-for="dict in options3"
              :key="dict.pid"
              :label="dict.pname"
              :value="dict.pid"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="服务人员" prop="clid">
          <el-select v-model="newForm.clid" placeholder="请选择收费方式" clearable>
            <el-option
              v-for="dict in options2"
              :key="dict.clid"
              :label="dict.clname"
              :value="dict.clid"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="日期" prop="sersdate" >
          <el-date-picker clearable
            v-model="newForm.sersdate"
            type="date"
            value-format="yyyy-MM-dd"
            @change="selectTime()"
            placeholder="请选择服务日期">
          </el-date-picker>
          <el-popover
            placement="right"
            title="不可使用时间"
            width="400"
            trigger="click">
            <ul>
              <li>
                <el-table :data="can_time">
                  <el-table-column property="serstime" label="开始时间" width="150"></el-table-column>
                  <el-table-column property="seretime" label="结束时间" width="150"></el-table-column>
                </el-table>
              </li>
            </ul>
            <el-button slot="reference">查询</el-button>
          </el-popover>
        </el-form-item>
        <el-form-item label="开始时间" prop="serstime" >
          <el-date-picker clearable
            v-model="newForm.serstime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择服务开始时间">
          </el-date-picker>
        </el-form-item>
        
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submit">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCum_servicetp, getCum_servicetp, delCum_servicetp, addCum_servicetp, updateCum_servicetp } from "@/api/system/cum_servicetp";
import { selectAllAdmManager } from "@/api/system/adm_manager";
import { listAdm_clerk } from"@/api/system/adm_clerk";
import { selectTime } from "@/api/system/man_service";
import { addCum_service, selectPet } from "@/api/system/cum_service";
export default {
  name: "Cum_servicetp",
  dicts: ['sys_shoufei'],
  data() {
    return {
      options1:[],//店铺
      options2:[],//店员
      options3:[],//
      newForm:{},
      can_time: null,
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 服务种类表格数据
      cum_servicetpList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      book: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        stname: null,
        stmoney: null,
        stmtp: null,
        stps: null,
        msname: null,
        mid:null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        clid: [
          { required: true, message: "未选中店员", trigger: "change" }
        ],
        pid: [
          { required: true, message: "未选中宠物", trigger: "change" }
        ],
        serstime: [
          { required: true, message: "未选择时间", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.selectAllAdmManager();
  },
  methods: {
    selectTime(){
      console.log(this.newForm);
      selectTime(this.newForm).then(response=>{
        console.log(response);
        this.can_time = response.rows.map(item => ({
          serstime: item.serstime,
          seretime: item.seretime
        })); 
      })
    },
    listCum_pet(){
      selectPet(this.newForm).then(response=>{
        this.options3=response.rows;
      });
    },
    selectAllAdmManager(){
      selectAllAdmManager().then(response => {
        this.options1=response.rows;
      });
    },
    /** 查询服务种类列表 */
    getList() {
      this.loading = true;
      listCum_servicetp(this.queryParams).then(response => {
        this.cum_servicetpList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.book = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        stid: null,
        stname: null,
        stmoney: null,
        stmtp: null,
        stps: null,
        clid: null,
        stime: null,
        sdate: null,
        serstime:null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.stid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const stid = row.stid || this.ids
      getCum_servicetp(stid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "查看服务信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.stid != null) {
            updateCum_servicetp(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCum_servicetp(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/cum_servicetp/export', {
        ...this.queryParams
      }, `cum_servicetp_${new Date().getTime()}.xlsx`)
    },
    bookNew(row) {
      this.newForm.stid=row.stid;
      this.newForm.mid=this.queryParams.mid;
      this.queryParams.stid=row.stid;
      this.listCum_pet();
      console.log(this.newForm);
      this.book=true;
      listAdm_clerk(this.queryParams).then(response => {
        this.options2 = response.rows;
      });
    },
    submit(){
      console.log(this.newForm);
      addCum_service(this.newForm).then(response => {
        this.$modal.msgSuccess("新增成功");
        this.book = false;
        this.getList();
      });
    }
  }
};
</script>
