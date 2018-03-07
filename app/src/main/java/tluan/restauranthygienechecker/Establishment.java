package tluan.restauranthygienechecker;

import org.json.JSONException;
import org.json.JSONObject;

public class Establishment {
    private String FHRSID;
    //private String localAuthorityBusinessID;
    private String businessName;
    private String businessType;
    private String addressLine;
    //private String addressLine1;
    //private String AddressLine2;
    //private String AddressLine3;
    //private String AddressLine4;
    private String localAuthorityName;
    private String localAuthorityEmailAddress;
    private String ratingValue;
    private String lngStr;
    private String latStr;
    private boolean hasLatLng;
    private Double lng;
    private Double lat;

    private JSONObject jObj;

    public Establishment(String FHRSID, String businessName,
                         String businessType, String addressLine, String localAuthorityName,
                         String localAuthorityEmailAddress, String ratingValue, String lngStr,
                         String latStr) {
        this.FHRSID = FHRSID;
        this.businessName = businessName;
        this.businessType = businessType;
        this.addressLine = addressLine;
        this.localAuthorityName = localAuthorityName;
        this.localAuthorityEmailAddress = localAuthorityEmailAddress;
        this.ratingValue = ratingValue;
        this.lngStr = lngStr;
        this.latStr = latStr;

        if (latStr !=null && lngStr != null && !latStr.equals("null") && !lngStr.equals("null")) {
            lat = Double.parseDouble(latStr);
            lng = Double.parseDouble(lngStr);
            hasLatLng = true;
        } else {
            lng = null;
            lat = null;
            hasLatLng = false;
        }
    }

    public Establishment (JSONObject jObj) throws JSONException {

        this(jObj.getString("FHRSID"), jObj.getString("BusinessName"),
                    jObj.getString("BusinessType"), jObj.getString("AddressLine1") +
                    jObj.getString("AddressLine2")+jObj.getString("AddressLine3")+ jObj.getString("AddressLine4"),
                    jObj.getString("LocalAuthorityName"),jObj.getString("LocalAuthorityEmailAddress"),
                    jObj.getString("RatingValue"), jObj.getJSONObject("geocode").getString("longitude"),
                    jObj.getJSONObject("geocode").getString("latitude"));
        this.jObj = jObj;

    }
    @Override
    public String toString() {
        return  getBusinessName();
    }

    public JSONObject getJsonObject() {
        return jObj;
    }

    public String getFHRSID() {
        return FHRSID;
    }

    public String getBusinessName() {
        return businessName;
    }

    public String getBusinessType() {
        return businessType;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public String getLocalAuthorityName() {
        return localAuthorityName;
    }

    public String getLocalAuthorityEmailAddress() {
        return localAuthorityEmailAddress;
    }

    public String getRatingValue() {
        return ratingValue;
    }

    public Double getLongitude() {
        return lng;
    }

    public Double getLatitude() {
        return lat;
    }

    public boolean hasLatLng() {
        return hasLatLng;
    }
    /*
            "LocalAuthorityBusinessID": "sample string 2",
            "BusinessName": "sample string 3",
            "BusinessType": "sample string 4",
            "BusinessTypeID": 5,
            "AddressLine1": "sample string 6",
            "AddressLine2": "sample string 7",
            "AddressLine3": "sample string 8",
            "AddressLine4": "sample string 9",
            "PostCode": "sample string 10",
            "Phone": "sample string 11",
            "RatingValue": "sample string 12",
            "RatingKey": "sample string 13",
            "RatingDate": "2018-03-02T17:57:10.2530949 00:00",
            "LocalAuthorityCode": "sample string 17",
            "LocalAuthorityName": "sample string 19",
            "LocalAuthorityWebSite": "sample string 20",
            "LocalAuthorityEmailAddress": "sample string 21",
            "scores": {
        "Hygiene": 1,
                "Structural": 1,
                "ConfidenceInManagement": 1
    },
            "SchemeType": "sample string 22",
            "geocode": {
        "longitude": "1.1",
                "latitude": "1.1"
    },
            "RightToReply": "sample string 23",
            "Distance": 1.1,
            "NewRatingPending": true,
            "meta": {
        "dataSource": "sample string 1",
                "extractDate": "2018-03-02T17:57:10.2530949 00:00",
                "itemCount": 3,
                "returncode": "sample string 4",
                "totalCount": 5,
                "totalPages": 6,
                "pageSize": 7,
                "pageNumber": 8
    },
            "links": [
    {
        "rel": "sample string 1",
            "href": "sample string 2"
    },
    {
        "rel": "sample string 1",
            "href": "sample string 2"
    },
    {
        "rel": "sample string 1",
            "href": "sample string 2"
    }
      ]
},
        {

        */
}
