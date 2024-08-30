import { fetchGet } from "@/utils/fetch/fetchWrapper";

export const fetchSearchProperty = async (propertyId) => {
  try {
    const response = await fetchGet(`/api/property/v1/property-detail/${propertyId}`, {}, {});

    if (response.responseCode === 'SUCCESS') {
      return response;
    } else {
      throw new Error(response.message || 'Error!');
    }
  } catch (error) {
    return new Error(error.message); // 에러 발생 시 null 반환
  }
}