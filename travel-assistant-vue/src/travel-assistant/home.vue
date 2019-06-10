<template>
  <el-row>
    <el-col :span="4">
      <CityCard
        :cityList="cityList"
        @rowClick="getTravelPlanList"
        @delete="deleteCityListRow"
        @add="addCityListRow">
      </CityCard>
    </el-col>
    <el-col :span="4">
      <PlanCard
        :planList="planList"
        @delete="deletePlanListRow"
      >
      </PlanCard>
    </el-col>
    <el-col :span="16">
      <div id="search-bar" v-if="currentCity.city">
        <el-autocomplete
          class="inline-input"
          v-model="place"
          :fetch-suggestions="querySearch"
          placeholder="请输入地点"
          prefix-icon="el-icon-search"
          clearable
          :trigger-on-focus="false"
          @select="handleSelect"
          @keyup.enter="handleSelect"
        ></el-autocomplete>
      </div>
      <Map
        :cityList="cityList"
        :currentCity="currentCity"
        :planList="planList"
      >
      </Map>
    </el-col>
  </el-row>
</template>

<script>

import CityCard from './city-card.vue'
import PlanCard from './plan-card.vue'
import Map from './map.vue'

export default {
  components: {
    CityCard,
    PlanCard,
    Map,
  },
  data() {
    return {
      userName: this.$cookies.get('userName'),
      // userName: 'admin',
      autoComplete: {},
      place: '',
      cityList: [],
      planList:[],
      currentCity: {},
      newMarker: '',
      delete: false
    }
  },
  methods: {
    planListApi(cityCode) {
      return this.api + cityCode + "/planList/";
    },
    getTravelPlanList(row) {
      if (this.delete === false) {
        var city = row.city;
        var cityCode = row.cityCode;
        this.currentCity = {
          city: city,
          cityCode: cityCode
        };
        this.$axios.get(this.planListApi(cityCode)).then(response => {
          this.planList = [];
          var planList = response.data;
          for (var i = 0; i < planList.length; i++) {
            this.planList.push({
              place: planList[i]
            });
          }
        })
        this.autoComplete.setCity(city);
        this.autoComplete.setCityLimit(true);
      }
      this.delete = false;
    },
    deleteCityListRow(index) {
      var cityCode = this.cityList[index].cityCode;
      var cityApi = this.api + cityCode;
      this.$axios.delete(cityApi);
      this.cityList.splice(index, 1);
      this.$message({
        type: 'success',
        message: '删除成功'
      });
      this.planList = [];
      this.delete = true;
    },
    addCityListRow(city) {
      var flag = true;
      for (var i = 0; i < this.cityList.length; i++) {
        if (this.cityList[i].city === city) {
          flag = false;
          break;
        }
      }
      if (flag) {
        AMap.plugin(['AMap.DistrictSearch'], () => {
          var citySearch = new AMap.DistrictSearch({
            level: 'city',
            subdistrict: 0
          });
          citySearch.search(city, (status, result) => {
            if (status === 'complete') {
              this.cityList.unshift({
                city: city,
                cityCode: result.districtList[0].citycode
              });
            }
          });
          this.planList = []; 
      });
      }
    },
    deletePlanListRow(index) {
      var data = {
        place: this.planList[index].place,
      }
      this.$axios.delete(this.planListApi(this.currentCity.cityCode), {params: data});
      this.planList.splice(index, 1);
      this.$message({
        type: 'success',
        message: '删除成功'
      })
    },
    querySearch(queryString ,cb) {
      this.autoComplete.search(queryString, (status, result) => {
        if (status === 'complete') {
          var tips = result.tips;
          var auto = [];
          for (var i = 0; i < tips.length; i++) {
            auto.push({
              value: tips[i].name
            })
          }
          cb(auto);
        }
      })
    },
    handleSelect() {
      var flag = false;
      for (var i = 0; i < this.planList.length; i++) {
        if (this.planList[i].place === this.place) {
          flag = true;
          break;
        }
      }
      if (flag === false) {
        var data = this.$qs.stringify({
          city: this.currentCity.city,
          place: this.place
        })
        this.$axios.post(this.planListApi(this.currentCity.cityCode), data);
        this.planList.push({
          place: this.place
        });
      }
      this.place = '';
    }
  },
  computed: {
    api() {
      return "http://localhost:8080/travel-assistant/api/" + this.userName + "/";
    },
    cityListApi() {
      return this.api + "cityList/";
    },
  },
  mounted() {
    this.$axios.get(this.cityListApi).then(response => {
      var cities = [];
      var cityList = response.data;
      for (var i = 0; i < cityList.length; i++) {
        var city = {
          city: cityList[i].city,
          cityCode: cityList[i].cityCode
        }
        cities.push(city);
      }
      this.cityList = cities;
    })
    AMap.plugin('AMap.Autocomplete', () => {
      this.autoComplete = new AMap.Autocomplete();
    });
  }
}
</script>

<style>
  #search-bar {
    position: absolute;
    z-index: 1;
    top: 26px;
    left: 450px;
    width: 250px;
    height: 50px;
  }
  .el-card {
    height: 540px;
  }
</style>
