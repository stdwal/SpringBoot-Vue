<template>
  <div id="container"></div>
</template>

<script>
export default {
  data() {
    return {
      map: {},
      markerList:[]
    }
  },

  props: ["cityList", "currentCity", "planList", "newMarker"],

  methods: {
    dist(marker1, marker2) {
      return (marker1.lng - marker2.lng) * (marker1.lng - marker2.lng) 
        + (marker1.lat - marker2.lat) * (marker1.lat - marker2.lat);
    }
  },
  computed: {
    sortedMarkerList() {
      if (this.planList.length > 0 && this.markerList.length === this.planList.length) {
        var minLng = this.markerList[0].lng;
        var idx = 0;
        for (var i = 1; i < this.markerList.length; i++) {
          if (this.markerList[i].lng < minLng) {
            minLng = this.markerList[i].lng;
            idx = i;
          }
        }
        [this.markerList[0], this.markerList[idx]] = [this.markerList[idx], this.markerList[0]];
        for (var i = 0; i < this.markerList.length - 1; i++) {
          var min = this.dist(this.markerList[i], this.markerList[i+1]);
          var mark = i + 1;
          for (var j = i + 1; j < this.markerList.length; j++) {
            var d = this.dist(this.markerList[i], this.markerList[j]);
            if (d < min) {
              min = d;
              mark = j;
            }
          }
          [this.markerList[i+1], this.markerList[mark]] = [this.markerList[mark], this.markerList[i+1]];
        }
        return this.markerList;
      }
      // if (this.markerList.length === this.planList.length) {
      //   return this.markerList.sort((a, b) => a.lng - b.lng);
      // }
    },
    passBy() {
      if (this.sortedMarkerList) {
        return this.sortedMarkerList.slice(1, this.sortedMarkerList.length - 1);
      }
    }
  },

  mounted() {
    this.map = new AMap.Map('container', {
      resizeEnable: true,
      zoom: 4,
      center: [104.1374349, 39.0713413],
      viewMode: '2D',
      lang:'zh_cn',
      keyboardEnable: false,
      mapStyle: "amap://styles/macaron",
    });
  },

  watch: {
    cityList() {
      this.map.clearMap();
      AMap.plugin(['AMap.DistrictSearch'], () => {
        var citySearch = new AMap.DistrictSearch({
          level: 'city',
          subdistrict: 0
        });
        for (var i = 0; i < this.cityList.length; i++) {
          citySearch.search(this.cityList[i].city, (status, result) => {
            if (status === 'complete') {
              var marker = new AMap.Marker({
                map: this.map,
                position: result.districtList[0].center
              });
              this.map.setFitView();
              if (this.map.getZoom() >= 10) {
                this.map.setZoom(10);
              }
            }
          });
        }
      });
    },
    currentCity() {
      AMap.plugin(['AMap.DistrictSearch'], () => {
        var citySearch = new AMap.DistrictSearch({
          level: 'city',
          subdistrict: 0
        });
        citySearch.search(this.currentCity.city, (status, result) => {
          if (status === 'complete') {
            var position = result.districtList[0].center;
            this.map.setCenter(position);
            this.map.setZoom(11);
          }
        })
      })
    },  
    planList() {
      this.map.clearMap();
      this.markerList = [];
      AMap.plugin(['AMap.PlaceSearch'], () => {
        var placeSearch = new AMap.PlaceSearch({
          city: this.currentCity.city,
          cityLimit: true,
        });
        for (var i = 0; i < this.planList.length; i++) {
          placeSearch.search(this.planList[i].place, (status, result) => {
            if (status === 'complete') {
              var position = result.poiList.pois[0].location;
              this.markerList.push(position);
              var marker = new AMap.Marker({
                map: this.map,
                position: position,
              });
              this.map.setFitView();
              if (this.map.getZoom() > 15) {
                this.map.setZoom(15);
              }
            }
          });
        }
      });
    },
    sortedMarkerList() {
      var n = this.markerList.length;
      if (n > 1) {
        AMap.plugin('AMap.Driving', () => {
          var driving = new AMap.Driving({
            policy: AMap.DrivingPolicy.LEAST_DISTANCE,
            hideMarkers: true,
            map: this.map,
            autoFitView: true,
          });
          var opt =  {
            waypoints: this.passBy
          }
          driving.search(this.sortedMarkerList[0], this.sortedMarkerList[n-1], 
            opt,(status, result) => {
              
            });
        })
      }
    },
  }
}

</script>

<style>
  #container {
    height: 540px;
  }
</style>