<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="店员姓名" prop="clname">
        <el-input
          v-model="queryParams.clname"
          placeholder="请输入店员姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="店员性别" prop="clsex">
        <el-select v-model="queryParams.clsex" placeholder="请选择店员性别" clearable>
          <el-option
            v-for="dict in dict.type.sys_user_sex"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="登录账号" prop="uname">
        <el-input
          v-model="queryParams.uname"
          placeholder="请输入店员登录账号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属店铺" prop="msname">
        <el-input
          v-model="queryParams.msname"
          placeholder="请输入所属店铺"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:adm_clerk:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:adm_clerk:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:adm_clerk:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:adm_clerk:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="adm_clerkList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="店员编号" align="center" prop="clid" />
      <el-table-column label="店员姓名" align="center" prop="clname" />
      <el-table-column label="店员性别" align="center" prop="clsex">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_user_sex" :value="scope.row.clsex"/>
        </template>
      </el-table-column>
      <el-table-column label="店员电话" align="center" prop="clnum" />
      <el-table-column label="所属店铺" align="center" prop="msname" />
      <el-table-column label="登录账号" align="center" prop="uname" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:adm_clerk:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:adm_clerk:remove']"
          >删除</el-button>
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

    <!-- 修改店员管理对话框 -->
    <el-dialog :title="title" :visible.sync="look" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="店员性别" prop="clsex">
          <el-select v-model="form.clsex" placeholder="请选择店员性别">
            <el-option
              v-for="dict in dict.type.sys_user_sex"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="登录账号" prop="uid">
          <el-select v-model="form.uid" placeholder="请选择登录账号">
            <el-option
              v-for="dict in options3"
              :key="dict.userId"
              :label="dict.userName"
              :value="parseInt(dict.userId)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="店员电话" prop="clnum">
          <el-input v-model="form.clnum" placeholder="请输入店员电话" />
        </el-form-item>
        <el-form-item label="所属店铺" prop="mid">
          <el-select v-model="form.mid" placeholder="请选择所属店铺">
            <el-option
              v-for="dict in options1"
              :key="dict.mid"
              :label="dict.msname"
              :value="parseInt(dict.mid)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-divider content-position="center">店员资质信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddAdmPsbsClkser">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteAdmPsbsClkser">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="admPsbsClkserList" :row-class-name="rowAdmPsbsClkserIndex" @selection-change="handleAdmPsbsClkserSelectionChange" ref="admPsbsClkser">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="服务种类名称" prop="stid" width="450">
            <template slot-scope="scope">
              <el-select v-model="scope.row.stid" placeholder="请选择服务种类">
                <el-option
                  v-for="dict in options2"
                  :key="dict.stid"
                  :label="dict.stname"
                  :value="parseInt(dict.stid)"
                ></el-option>
              </el-select>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 增加店员管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="登录账号" prop="uid">
          <el-select v-model="form.uid" placeholder="请选择登录账号">
            <el-option
              v-for="dict in options3"
              :key="dict.userId"
              :label="dict.userName"
              :value="parseInt(dict.userId)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="所属店铺" prop="mid">
          <el-select v-model="form.mid" placeholder="请选择所属店铺">
            <el-option
              v-for="dict in options1"
              :key="dict.mid"
              :label="dict.msname"
              :value="parseInt(dict.mid)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-divider content-position="center">店员资质信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddAdmPsbsClkser">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteAdmPsbsClkser">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="admPsbsClkserList" :row-class-name="rowAdmPsbsClkserIndex" @selection-change="handleAdmPsbsClkserSelectionChange" ref="admPsbsClkser">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="服务种类名称" prop="stid" width="450">
            <template slot-scope="scope">
              <el-select v-model="scope.row.stid" placeholder="请选择服务种类">
                <el-option
                  v-for="dict in options2"
                  :key="dict.stid"
                  :label="dict.stname"
                  :value="parseInt(dict.stid)"
                ></el-option>
              </el-select>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listAdm_clerk, getAdm_clerk, delAdm_clerk, addAdm_clerk, updateAdm_clerk } from "@/api/system/adm_clerk";
import { selectAllAdmManager } from "@/api/system/adm_manager";
import { selectAllAdmServicetp } from "@/api/system/adm_servicetp";
import { selectAllUserUngive } from "@/api/system/user";

export default {
  name: "Adm_clerk",
  dicts: ['sys_user_sex'],
  data() {
    return {
      options1:[],
      options2:[],
      options3:[],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedAdmPsbsClkser: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 店员管理表格数据
      adm_clerkList: [],
      // 店员服务表格数据
      admPsbsClkserList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      look: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        clid:null,
        clname:null,
        msname:null,
        clsex:null,
        uname:null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        clid: [
          { required: true, message: "店员编号不能为空", trigger: "blur" }
        ],
        clname: [
          { required: true, message: "店员姓名不能为空", trigger: "blur" }
        ],
        mid: [
          { required: true, message: "所属店铺不能为空", trigger: "blur" }
        ],
        uid: [
          { required: true, message: "登录账号不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.selectAllAdmManager();
    this.selectAllAdmServicetp();
    this.selectAllUserUngive();
  },
  methods: {
    selectAllAdmManager(){
      selectAllAdmManager().then(response => {
        this.options1=response.rows;
      });
    },
    selectAllAdmServicetp(){
      selectAllAdmServicetp().then(response => {
        this.options2=response.rows;
      });
    },
    selectAllUserUngive(){
      selectAllUserUngive().then(response => {
        this.options3=response.rows;
      });
    },
    /** 查询店员管理列表 */
    getList() {
      this.loading = true;
      listAdm_clerk(this.queryParams).then(response => {
        this.adm_clerkList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.look = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        clid: null,
        clname: null,
        clsex: null,
        clnum: null,
        mid: null,
        uid: null
      };
      this.admPsbsClkserList = [];
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
      this.ids = selection.map(item => item.clid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加店员管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const clid = row.clid || this.ids
      getAdm_clerk(clid).then(response => {
        console.log(response);
        this.form = response.data;
        this.admPsbsClkserList = response.data.admPsbsClkserList;
        this.look = true;
        this.title = "修改店员信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.admPsbsClkserList = this.admPsbsClkserList;
          if (this.form.clid != null) {
            updateAdm_clerk(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAdm_clerk(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const clids = row.clid || this.ids;
      this.$modal.confirm('是否确认删除店员管理编号为"' + clids + '"的数据项？').then(function() {
        return delAdm_clerk(clids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 店员服务序号 */
    rowAdmPsbsClkserIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 店员服务添加按钮操作 */
    handleAddAdmPsbsClkser() {
      let obj = {};
      obj.stid = "";
      this.admPsbsClkserList.push(obj);
    },
    /** 店员服务删除按钮操作 */
    handleDeleteAdmPsbsClkser() {
      if (this.checkedAdmPsbsClkser.length == 0) {
        this.$modal.msgError("请先选择要删除的服务资质");
      } else {
        const admPsbsClkserList = this.admPsbsClkserList;
        const checkedAdmPsbsClkser = this.checkedAdmPsbsClkser;
        this.admPsbsClkserList = admPsbsClkserList.filter(function(item) {
          return checkedAdmPsbsClkser.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleAdmPsbsClkserSelectionChange(selection) {
      this.checkedAdmPsbsClkser = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/adm_clerk/export', {
        ...this.queryParams
      }, `adm_clerk_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
