import { fetchGet } from "@/utils/fetch/fetchWrapper"

export const fetchSearchProperties = async (data) => {
  try {
    console.log(data)
    const response = await fetchGet(`/api/client/v1/showing-property-list`, {}, {
      addressLevel1Id: data.addressL1,
      addressLevel2Id: data.addressL2,
      propertyTransactionTypeId: data.transactionTypeId,
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
