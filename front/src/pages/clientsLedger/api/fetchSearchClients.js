import { fetchGet } from "@/utils/fetch/fetchWrapper"

export const fetchSearchClients = async (clientName, clientPhoneNumber) => {
  try {
    const response = await fetchGet(`/api/client/v1/client-list`, {}, {
      clientName : clientName,
      clientPhoneNumber : clientPhoneNumber
    });

    if (response.responseCode === 'SUCCESS') {
      return response;
    } else {
      throw new Error(response.message || 'Error!');
    }
  } catch (error) {
    return new Error(error.message); // 에러 발생 시 null 반환
  }
}