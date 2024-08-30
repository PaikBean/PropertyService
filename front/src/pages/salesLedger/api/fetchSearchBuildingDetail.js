import { fetchGet } from "@/utils/fetch/fetchWrapper";

export const fetchSearchBuildingDetail = async (buildingId) => {
  try {
    const response = await fetchGet(`/api/building/v1/building-detail/${buildingId}`, {}, {});

    if (response.responseCode === 'SUCCESS') {
      return response;
    } else {
      throw new Error(response.message || 'Error!');
    }
  } catch (error) {
    return new Error(error.message); // 에러 발생 시 null 반환
  }
}