package tluan.restauranthygienechecker;

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
    private int scoreHygiene;
    private String longitude;
    private String latitude;

    public Establishment(String FHRSID, String businessName,
                         String businessType, String addressLine, String localAuthorityName,
                         String localAuthorityEmailAddress, int scoreHygiene, String longitude,
                         String latitude) {
        this.FHRSID = FHRSID;
        this.businessName = businessName;
        this.businessType = businessType;
        this.addressLine = addressLine;
        this.localAuthorityName = localAuthorityName;
        this.localAuthorityEmailAddress = localAuthorityEmailAddress;
        this.scoreHygiene = scoreHygiene;
        this.longitude = longitude;
        this.latitude = latitude;
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

    public int getScoreHygiene() {
        return scoreHygiene;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
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
