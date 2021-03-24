<template>
  <div>
    <el-row> 
      <el-col :span="12">
        <h3>旅行目的地类型分析</h3>
        <ve-ring :data="placePreferenceChartData"></ve-ring>
      </el-col>
      <el-col :span="12">
        <h3>旅行城市及目的地数量</h3>
        <p>你已经走遍了全国{{cityCount}}个城市</p>
        <p>去到了{{placeCount}}个目的地</p>
        <ve-histogram :data="cityPlaceCountChartData"></ve-histogram>
      </el-col>
    </el-row>
    <el-row>
      <h3>旅行周期分析</h3>
      <ve-line :data="newPlaceChartData"></ve-line>
    </el-row>
  </div>
</template>

<script>
  export default {
    data: function () {
      return {
        userName: this.$cookies.get('userName'),
        // userName: 'admin',
        cityCount: 0,
        placeCount: 0,
        newPlaceChartData: {
          columns: [],
          rows: []
        },
        placePreferenceChartData: {
          columns: [],
          rows: []
        },
        cityPlaceCountChartData: {
          columns: [],
          rows: []
        }
      }
    },
    computed: {
      api() {
        return "http://localhost:8080/travel-assistant/api/" + this.userName + "/";
      }
    },
    mounted() {
      this.$axios.get(this.api + 'cityCount').then(response => {
        this.cityCount = response.data;
      });

      this.$axios.get(this.api + 'placeCount').then(response => {
        this.placeCount = response.data;
      });

      this.$axios.get(this.api + 'newPlaceChart').then(response => {
        var newPlaceChartData = response.data;
        this.newPlaceChartData.columns = newPlaceChartData.columns;
        for (var i = 0; i < newPlaceChartData.rows.length; i++) {
          this.newPlaceChartData.rows.push({
            '日期': newPlaceChartData.rows[i].date,
            '新增计划': newPlaceChartData.rows[i].count
          })
        }
      });

      this.$axios.get(this.api + 'cityPlaceCountChart').then(response => {
        var cityPlaceCountChartData = response.data;
        this.cityPlaceCountChartData.columns = cityPlaceCountChartData.columns;
        for (var i = 0; i < cityPlaceCountChartData.rows.length; i++) {
          this.cityPlaceCountChartData.rows.push({
            '城市': cityPlaceCountChartData.rows[i].city,
            '地点': cityPlaceCountChartData.rows[i].count
          })
        }
      });


      this.$axios.get(this.api + 'placePreferenceChart').then(response => {
        var placePreferenceChartData = response.data;
        this.placePreferenceChartData.columns = placePreferenceChartData.columns;
        for (var i = 0; i < placePreferenceChartData.rows.length; i++) {
          this.placePreferenceChartData.rows.push({
            '类型': placePreferenceChartData.rows[i].type,
            '数量': placePreferenceChartData.rows[i].count
          })
        }
      });
    }
  }
</script>