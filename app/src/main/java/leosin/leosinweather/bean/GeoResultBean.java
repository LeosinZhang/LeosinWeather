package leosin.leosinweather.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Author: LeosinZhang
 * Time: 2017/5/16 11:13
 * Describle:
 */
public class GeoResultBean implements Serializable{

    /**
     * status : 0
     * result : {"location":{"lng":104.06792345999995,"lat":30.67994271991261},"formatted_address":"四川省成都市青羊区王家塘街84号","business":"骡马市,新华西路,八宝街","addressComponent":{"country":"中国","country_code":0,"province":"四川省","city":"成都市","district":"青羊区","adcode":"510105","street":"王家塘街","street_number":"84号","direction":"附近","distance":"6"},"pois":[{"addr":"成都市新都区蜀龙路保利198公园对面驿站路二台子驿站F6-F7栋","cp":" ","direction":"附近","distance":"1","name":"成都华氏陶瓷艺术博物馆","poiType":"旅游景点","point":{"x":104.06792076838752,"y":30.679934847832687},"tag":"旅游景点;博物馆","tel":"","uid":"9c34e79d6fa216ccd8eced05","zip":""},{"addr":"成都市江汉路222号","cp":" ","direction":"西南","distance":"179","name":"青羊区政府","poiType":"政府机构","point":{"x":104.0687292433465,"y":30.681153889762957},"tag":"政府机构;各级政府","tel":"","uid":"96b672aa58335874cf04ef80","zip":""},{"addr":"江汉路224","cp":" ","direction":"南","distance":"61","name":"成都市青羊区监察局","poiType":"政府机构","point":{"x":104.06789381922222,"y":30.680416254783125},"tag":"政府机构;行政单位","tel":"","uid":"f633606adc167d300b69d9d5","zip":""},{"addr":"万和路90号(天象大厦)步行10分钟至文殊院地铁站/宽窄巷子景区","cp":" ","direction":"东","distance":"67","name":"成都嘉立假日酒店（万和路店）","poiType":"酒店","point":{"x":104.06731890369583,"y":30.679965906418758},"tag":"酒店;快捷酒店","tel":"","uid":"9d0f946311f4566b3fe231e9","zip":""},{"addr":"万和路90号天象大厦6层、7层B区","cp":" ","direction":"东","distance":"83","name":"成都市青羊区地税局","poiType":"政府机构","point":{"x":104.06719314092445,"y":30.680082376026693},"tag":"政府机构;行政单位","tel":"","uid":"d01d36a0c5b6644a8c55ec36","zip":""},{"addr":"青羊区万和路90号","cp":" ","direction":"东","distance":"81","name":"天象大厦","poiType":"房地产","point":{"x":104.06720212397954,"y":30.679833907358272},"tag":"房地产;写字楼","tel":"","uid":"f9fd60620770d9630f0e6b13","zip":""},{"addr":"江汉路224","cp":" ","direction":"西","distance":"128","name":"成都市青羊区档案局","poiType":"政府机构","point":{"x":104.0690616163852,"y":30.68010566993125},"tag":"政府机构;行政单位","tel":"","uid":"af1ae56b86e9b0104307c195","zip":""},{"addr":"四川省成都市青羊区江汉路226号","cp":" ","direction":"西南","distance":"179","name":"中国农业银行(新华支行)","poiType":"金融","point":{"x":104.0687292433465,"y":30.681153889762957},"tag":"金融;银行","tel":"","uid":"c879fa014ed9684bc909f0f6","zip":""},{"addr":"八宝街19","cp":" ","direction":"北","distance":"210","name":"八宝广场","poiType":"休闲娱乐","point":{"x":104.06723805619994,"y":30.678420729527637},"tag":"休闲娱乐;休闲广场","tel":"","uid":"c6415a04ca1640c526e0a0f8","zip":""},{"addr":"万和路7","cp":" ","direction":"东北","distance":"177","name":"青羊区人民医院-急诊","poiType":"医疗","point":{"x":104.06653737790215,"y":30.679267085791917},"tag":"医疗;其他","tel":"","uid":"3cced63a9db90c0eeb56fa2b","zip":""}],"poiRegions":[],"sematic_description":"成都华氏陶瓷艺术博物馆附近1米","cityCode":75}
     */

    private int status;
    private ResultBean result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * location : {"lng":104.06792345999995,"lat":30.67994271991261}
         * formatted_address : 四川省成都市青羊区王家塘街84号
         * business : 骡马市,新华西路,八宝街
         * addressComponent : {"country":"中国","country_code":0,"province":"四川省","city":"成都市","district":"青羊区","adcode":"510105","street":"王家塘街","street_number":"84号","direction":"附近","distance":"6"}
         * pois : [{"addr":"成都市新都区蜀龙路保利198公园对面驿站路二台子驿站F6-F7栋","cp":" ","direction":"附近","distance":"1","name":"成都华氏陶瓷艺术博物馆","poiType":"旅游景点","point":{"x":104.06792076838752,"y":30.679934847832687},"tag":"旅游景点;博物馆","tel":"","uid":"9c34e79d6fa216ccd8eced05","zip":""},{"addr":"成都市江汉路222号","cp":" ","direction":"西南","distance":"179","name":"青羊区政府","poiType":"政府机构","point":{"x":104.0687292433465,"y":30.681153889762957},"tag":"政府机构;各级政府","tel":"","uid":"96b672aa58335874cf04ef80","zip":""},{"addr":"江汉路224","cp":" ","direction":"南","distance":"61","name":"成都市青羊区监察局","poiType":"政府机构","point":{"x":104.06789381922222,"y":30.680416254783125},"tag":"政府机构;行政单位","tel":"","uid":"f633606adc167d300b69d9d5","zip":""},{"addr":"万和路90号(天象大厦)步行10分钟至文殊院地铁站/宽窄巷子景区","cp":" ","direction":"东","distance":"67","name":"成都嘉立假日酒店（万和路店）","poiType":"酒店","point":{"x":104.06731890369583,"y":30.679965906418758},"tag":"酒店;快捷酒店","tel":"","uid":"9d0f946311f4566b3fe231e9","zip":""},{"addr":"万和路90号天象大厦6层、7层B区","cp":" ","direction":"东","distance":"83","name":"成都市青羊区地税局","poiType":"政府机构","point":{"x":104.06719314092445,"y":30.680082376026693},"tag":"政府机构;行政单位","tel":"","uid":"d01d36a0c5b6644a8c55ec36","zip":""},{"addr":"青羊区万和路90号","cp":" ","direction":"东","distance":"81","name":"天象大厦","poiType":"房地产","point":{"x":104.06720212397954,"y":30.679833907358272},"tag":"房地产;写字楼","tel":"","uid":"f9fd60620770d9630f0e6b13","zip":""},{"addr":"江汉路224","cp":" ","direction":"西","distance":"128","name":"成都市青羊区档案局","poiType":"政府机构","point":{"x":104.0690616163852,"y":30.68010566993125},"tag":"政府机构;行政单位","tel":"","uid":"af1ae56b86e9b0104307c195","zip":""},{"addr":"四川省成都市青羊区江汉路226号","cp":" ","direction":"西南","distance":"179","name":"中国农业银行(新华支行)","poiType":"金融","point":{"x":104.0687292433465,"y":30.681153889762957},"tag":"金融;银行","tel":"","uid":"c879fa014ed9684bc909f0f6","zip":""},{"addr":"八宝街19","cp":" ","direction":"北","distance":"210","name":"八宝广场","poiType":"休闲娱乐","point":{"x":104.06723805619994,"y":30.678420729527637},"tag":"休闲娱乐;休闲广场","tel":"","uid":"c6415a04ca1640c526e0a0f8","zip":""},{"addr":"万和路7","cp":" ","direction":"东北","distance":"177","name":"青羊区人民医院-急诊","poiType":"医疗","point":{"x":104.06653737790215,"y":30.679267085791917},"tag":"医疗;其他","tel":"","uid":"3cced63a9db90c0eeb56fa2b","zip":""}]
         * poiRegions : []
         * sematic_description : 成都华氏陶瓷艺术博物馆附近1米
         * cityCode : 75
         */

        private LocationBean location;
        private String formatted_address;
        private String business;
        private AddressComponentBean addressComponent;
        private String sematic_description;
        private int cityCode;
        private List<PoisBean> pois;
        private List<?> poiRegions;

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public String getFormatted_address() {
            return formatted_address;
        }

        public void setFormatted_address(String formatted_address) {
            this.formatted_address = formatted_address;
        }

        public String getBusiness() {
            return business;
        }

        public void setBusiness(String business) {
            this.business = business;
        }

        public AddressComponentBean getAddressComponent() {
            return addressComponent;
        }

        public void setAddressComponent(AddressComponentBean addressComponent) {
            this.addressComponent = addressComponent;
        }

        public String getSematic_description() {
            return sematic_description;
        }

        public void setSematic_description(String sematic_description) {
            this.sematic_description = sematic_description;
        }

        public int getCityCode() {
            return cityCode;
        }

        public void setCityCode(int cityCode) {
            this.cityCode = cityCode;
        }

        public List<PoisBean> getPois() {
            return pois;
        }

        public void setPois(List<PoisBean> pois) {
            this.pois = pois;
        }

        public List<?> getPoiRegions() {
            return poiRegions;
        }

        public void setPoiRegions(List<?> poiRegions) {
            this.poiRegions = poiRegions;
        }

        public static class LocationBean {
            /**
             * lng : 104.06792345999995
             * lat : 30.67994271991261
             */

            private double lng;
            private double lat;

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }
        }

        public static class AddressComponentBean {
            /**
             * country : 中国
             * country_code : 0
             * province : 四川省
             * city : 成都市
             * district : 青羊区
             * adcode : 510105
             * street : 王家塘街
             * street_number : 84号
             * direction : 附近
             * distance : 6
             */

            private String country;
            private int country_code;
            private String province;
            private String city;
            private String district;
            private String adcode;
            private String street;
            private String street_number;
            private String direction;
            private String distance;

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public int getCountry_code() {
                return country_code;
            }

            public void setCountry_code(int country_code) {
                this.country_code = country_code;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public String getAdcode() {
                return adcode;
            }

            public void setAdcode(String adcode) {
                this.adcode = adcode;
            }

            public String getStreet() {
                return street;
            }

            public void setStreet(String street) {
                this.street = street;
            }

            public String getStreet_number() {
                return street_number;
            }

            public void setStreet_number(String street_number) {
                this.street_number = street_number;
            }

            public String getDirection() {
                return direction;
            }

            public void setDirection(String direction) {
                this.direction = direction;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }
        }

        public static class PoisBean {
            /**
             * addr : 成都市新都区蜀龙路保利198公园对面驿站路二台子驿站F6-F7栋
             * cp :
             * direction : 附近
             * distance : 1
             * name : 成都华氏陶瓷艺术博物馆
             * poiType : 旅游景点
             * point : {"x":104.06792076838752,"y":30.679934847832687}
             * tag : 旅游景点;博物馆
             * tel :
             * uid : 9c34e79d6fa216ccd8eced05
             * zip :
             */

            private String addr;
            private String cp;
            private String direction;
            private String distance;
            private String name;
            private String poiType;
            private PointBean point;
            private String tag;
            private String tel;
            private String uid;
            private String zip;

            public String getAddr() {
                return addr;
            }

            public void setAddr(String addr) {
                this.addr = addr;
            }

            public String getCp() {
                return cp;
            }

            public void setCp(String cp) {
                this.cp = cp;
            }

            public String getDirection() {
                return direction;
            }

            public void setDirection(String direction) {
                this.direction = direction;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPoiType() {
                return poiType;
            }

            public void setPoiType(String poiType) {
                this.poiType = poiType;
            }

            public PointBean getPoint() {
                return point;
            }

            public void setPoint(PointBean point) {
                this.point = point;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getZip() {
                return zip;
            }

            public void setZip(String zip) {
                this.zip = zip;
            }

            public static class PointBean {
                /**
                 * x : 104.06792076838752
                 * y : 30.679934847832687
                 */

                private double x;
                private double y;

                public double getX() {
                    return x;
                }

                public void setX(double x) {
                    this.x = x;
                }

                public double getY() {
                    return y;
                }

                public void setY(double y) {
                    this.y = y;
                }
            }
        }
    }
}
