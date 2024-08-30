import { fetchGet } from '@/utils/fetch/fetchWrapper'

export const fetchValidBizNumber = async (data) => {
  try {
    const queryParams = {
      bNo: data.bizNumber,
      startDate: data.bizStartDate,
      pName: data.presidentName,
    }
    const response = await fetchGet(
      '/api/company/v1/validate/biz-number',
      {},
      queryParams
    )
    if (response.responseCode === 'SUCCESS') {
      return response
    } else {
      throw new Error(response.code || 'Error!')
    }
  } catch (error) {
    // console.error(error.message)
    return new Error(error) // 에러 발생 시 null 반환
  }
}
