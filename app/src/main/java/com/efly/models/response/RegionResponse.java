package com.efly.models.response;

import java.util.List;

public class RegionResponse {

    private List<RegionFetched> available_regions;

    public List<RegionFetched> getAvailable_regions() {
        return available_regions;
    }

    public void setAvailable_regions(List<RegionFetched> available_regions) {
        this.available_regions = available_regions;
    }
}
