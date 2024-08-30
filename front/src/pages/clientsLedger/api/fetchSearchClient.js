import { fetchGet } from "@/utils/fetch/fetchWrapper";

// InflowType 매핑 객체 정의
const inflowTypeMapping = {
  "직방": "ZIGBANG",
  "다방": "DABANG",
  "피터팬": "PETERPANZ",
  "집토스": "ZIPTOSS",
  "기타": "OTHERS"
};

export const fetchSearchClient = async (clientId) => {
  try {
    const response = await fetchGet(`/api/client/v1/client/${clientId}`, {}, {});

    if (response.responseCode === 'SUCCESS') {
      // inflowType 값 변환
      const inflowType = response.data.inflowType;
      if (inflowTypeMapping[inflowType]) {
        response.data.inflowType = inflowTypeMapping[inflowType];
      }
      
      return response;
    } else {
      throw new Error(response.message || 'Error!');
    }
  } catch (error) {
    return new Error(error.message); // 에러 발생 시 null 반환
  }
}