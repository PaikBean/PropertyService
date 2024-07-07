const { fetchPost } = require('@/utils/fetch/fetchWrapper')

export const fetchRegistCompany = async (data) => {
  try {
    const response = await fetchPost('/api/company/v1/company', data)
    if (response.responseCode === 'SUCCESS') {
      return response
    } else {
      throw new Error(response.message || 'Error!')
    }
  } catch (error) {
    return new Error(error.message) // 에러 발생 시 null 반환
  }
}
