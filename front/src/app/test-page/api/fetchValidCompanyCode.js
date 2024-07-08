import { fetchGet } from '@/utils/fetch/fetchWrapper'

export const fetchValidCompanyCode = async (data) => {
  try {
    const queryParams = {
      companyCode: data,
    }
    const response = await fetchGet(
      '/api/company/v1/validate/company-code',
      {},
      queryParams
    )

    if (response.responseCode === 'SUCCESS') {
      return response
    } else {
      throw new Error(response.message || 'Error!')
    }
  } catch (error) {
    return new Error(error.message) // 에러 발생 시 null 반환
  }
}
