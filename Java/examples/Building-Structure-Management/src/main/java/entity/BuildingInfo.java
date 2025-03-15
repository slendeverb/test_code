package entity;

public class BuildingInfo {
    private Integer id;
    private String buildingName;
    private String buildingLocation;
    private String buildingType;
    private String buildingStructure;
    private String buildingTerm;

    public BuildingInfo() {
    }

    public BuildingInfo(int id, String buildingName, String buildingLocation, String buildingType, String buildingStructure, String buildingTerm) {
        this.id = id;
        this.buildingName = buildingName;
        this.buildingLocation = buildingLocation;
        this.buildingType = buildingType;
        this.buildingStructure = buildingStructure;
        this.buildingTerm = buildingTerm;
    }

    public BuildingInfo(String buildingName, String buildingLocation, String buildingType, String buildingStructure, String buildingTerm) {
        this.buildingName = buildingName;
        this.buildingLocation = buildingLocation;
        this.buildingType = buildingType;
        this.buildingStructure = buildingStructure;
        this.buildingTerm = buildingTerm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getBuildingLocation() {
        return buildingLocation;
    }

    public void setBuildingLocation(String buildingLocation) {
        this.buildingLocation = buildingLocation;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public String getBuildingStructure() {
        return buildingStructure;
    }

    public void setBuildingStructure(String buildingStructure) {
        this.buildingStructure = buildingStructure;
    }

    public String getBuildingTerm() {
        return buildingTerm;
    }

    public void setBuildingTerm(String buildingTerm) {
        this.buildingTerm = buildingTerm;
    }
}
