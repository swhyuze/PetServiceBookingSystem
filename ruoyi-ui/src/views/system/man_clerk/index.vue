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
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:man_clerk:remove']"
        >删除</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="man_clerkList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="店员编号" align="center" prop="clid" />
      <el-table-column label="店员姓名" align="center" prop="clname" />
      <el-table-column label="店员性别" align="center" prop="clsex">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_user_sex" :value="scope.row.clsex"/>
        </template>
      </el-table-column>
      <el-table-column label="店员电话" align="center" prop="clnum" />
      <el-table-column label="登录账号" align="center" prop="uname" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:man_clerk:edit']"
          >修改</el-button>
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

    <!-- 添加或修改店员管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="店员姓名" prop="clname">
          <el-input v-model="form.clname" placeholder="请输入店员姓名" />
        </el-form-item>
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
        <el-divider content-position="center">店员服务信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddManPsbsClkser">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteManPsbsClkser">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="manPsbsClkserList" :row-class-name="rowManPsbsClkserIndex" @selection-change="handleManPsbsClkserSelectionChange" ref="manPsbsClkser">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="服务种类名称" prop="stid" width="450">
            <template slot-scope="scope">
              <el-select v-model="scope.row.stid" placeholder="请选择服务种类">
                <el-option
                  v-for="dict in options"
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
import { listMan_clerk, getMan_clerk, delMan_clerk, addMan_clerk, updateMan_clerk } from "@/api/system/man_clerk";
import { selectAllAdmServicetp } from "@/api/system/adm_servicetp";

export default {
  name: "Man_clerk",
  dicts: ['sys_user_sex'],
  data() {
    return {
      options:[],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedManPsbsClkser: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 店员管理表格数据
      man_clerkList: [],
      // 店员服务表格数据
      manPsbsClkserList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        clname: null,
        clsex: null,
        clnum: null,
        mid: null,
        uid: null
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
          { required: true, message: "上司编号不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.selectAllAdmServicetp();
  },
  methods: {
    selectAllAdmServicetp(){
      selectAllAdmServicetp().then(response => {
        console.log(response);
        this.options=response.rows;
      });
    },
    /** 查询店员管理列表 */
    getList() {
      this.loading = true;
      listMan_clerk(this.queryParams).then(response => {
        this.man_clerkList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
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
      this.manPsbsClkserList = [];
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
      getMan_clerk(clid).then(response => {
        this.form = response.data;
        this.manPsbsClkserList = response.data.manPsbsClkserList;
        this.open = true;
        this.title = "修改店员管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.manPsbsClkserList = this.manPsbsClkserList;
          if (this.form.clid != null) {
            updateMan_clerk(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMan_clerk(this.form).then(response => {
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
        return delMan_clerk(clids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 店员服务序号 */
    rowManPsbsClkserIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 店员服务添加按钮操作 */
    handleAddManPsbsClkser() {
      let obj = {};
      obj.stid = "";
      this.manPsbsClkserList.push(obj);
    },
    /** 店员服务删除按钮操作 */
    handleDeleteManPsbsClkser() {
      if (this.checkedManPsbsClkser.length == 0) {
        this.$modal.msgError("请先选择要删除的店员服务数据");
      } else {
        const manPsbsClkserList = this.manPsbsClkserList;
        const checkedManPsbsClkser = this.checkedManPsbsClkser;
        this.manPsbsClkserList = manPsbsClkserList.filter(function(item) {
          return checkedManPsbsClkser.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleManPsbsClkserSelectionChange(selection) {
      this.checkedManPsbsClkser = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/man_clerk/export', {
        ...this.queryParams
      }, `man_clerk_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
