<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="服务种类" prop="stname">
        <el-input
          v-model="queryParams.stname"
          placeholder="请输入服务种类"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="宠物种类" prop="ptname">
        <el-input
          v-model="queryParams.ptname"
          placeholder="请输入宠物种类"
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
          v-hasPermi="['system:adm_petser:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:adm_petser:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:adm_petser:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="adm_petserList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="psid" />
      <el-table-column label="服务种类" align="center" prop="stname" />
      <el-table-column label="宠物种类" align="center" prop="ptname" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:adm_petser:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:adm_petser:remove']"
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

    <!-- 添加或修改宠物服务对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="服务编号" prop="stid">
          <el-select v-model="form.stid" placeholder="请选择服务种类">
            <el-option
              v-for="dict in option2"
              :key="dict.stid"
              :label="dict.stname"
              :value="parseInt(dict.stid)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="宠物品种" prop="ptid">
          <el-select v-model="form.ptid" placeholder="请选择宠物品种">
            <el-option
              v-for="dict in option1"
              :key="dict.ptid"
              :label="dict.ptname"
              :value="parseInt(dict.ptid)"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listAdm_petser, getAdm_petser, delAdm_petser, addAdm_petser, updateAdm_petser } from "@/api/system/adm_petser";
import { selectAdmPsbsPettpAll } from "@/api/system/cum_pet";
import { selectAllAdmServicetp } from "@/api/system/adm_servicetp";
export default {
  name: "Adm_petser",
  data() {
    return {
      option1:[],
      option2:[],
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
      // 宠物服务表格数据
      adm_petserList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        stid: null,
        ptid: null,
        stname: null,
        ptname: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.selectAdmPsbsPettpAll();
    this.selectAllAdmServicetp();
  },
  methods: {
    selectAdmPsbsPettpAll() {
      selectAdmPsbsPettpAll().then(response => {
        console.log(response);
        this.option1=response.rows;
      });
    },
    selectAllAdmServicetp(){
      selectAllAdmServicetp().then(response => {
        console.log(response);
        this.option2=response.rows;
      });
    },
    /** 查询宠物服务列表 */
    getList() {
      this.loading = true;
      listAdm_petser(this.queryParams).then(response => {
        this.adm_petserList = response.rows;
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
        psid: null,
        stid: null,
        ptid: null
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
      this.ids = selection.map(item => item.psid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加宠物服务";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const psid = row.psid || this.ids
      getAdm_petser(psid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改宠物服务";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.psid != null) {
            updateAdm_petser(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAdm_petser(this.form).then(response => {
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
      const psids = row.psid || this.ids;
      this.$modal.confirm('是否确认删除宠物服务编号为"' + psids + '"的数据项？').then(function() {
        return delAdm_petser(psids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/adm_petser/export', {
        ...this.queryParams
      }, `adm_petser_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
