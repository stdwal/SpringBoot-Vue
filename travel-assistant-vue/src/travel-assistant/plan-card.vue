<template>
  <el-card class="box-card" id="planCard">
    <el-table
      :data="planList"
      height="500"
    >
      <el-table-column 
        prop="place" 
        label="目的地"
      >
      </el-table-column>
      <el-table-column align="right">
        <template slot-scope="scope">
          <el-button 
            size="mini" 
            icon="el-icon-delete"
            circle
            @click.native.prevent="deletePlanListRow(scope.$index)"
          />
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script>
export default {
  props: ["planList"],
  
  methods : {
    deletePlanListRow(index) {
        this.$emit('delete', index);
    },
  },
}
</script>

<style>
#planCard {
  width: 200px;
}
</style>
