import { fetchGet } from "@/utils/fetch/fetchWrapper"

export const fetchSearchClients = async (clientName, clientPhoneNumber) => {
  try {
    // 파라미터 객체를 생성
    const params = {};

    // 조건에 따라 파라미터를 추가
    if (clientName && clientName !== "undefined") {
      params.clientName = clientName;
    }

    if (clientPhoneNumber && clientPhoneNumber !== "undefined") {
      params.clientPhoneNumber = clientPhoneNumber;
    }

    // fetchGet 요청을 파라미터와 함께 전송
    const response = await fetchGet(`/api/client/v1/client-list`, {}, params);

    if (response.responseCode === 'SUCCESS') {
      return response;
    } else {
      throw new Error(response.message || 'Error!');
    }
  } catch (error) {
    return new Error(error.message); // 에러 발생 시 null 반환
  }
}