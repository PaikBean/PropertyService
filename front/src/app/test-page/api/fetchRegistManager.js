const { fetchPost } = require('@/utils/fetch/fetchWrapper')

export const fetchREgistManager = async (data) => {
  try {
    const response = await fetchPost('/api/manager/v1/sign-up', data)
    if (response.responseCode === 'SUCCESS') {
      return response
    } else {
      throw new Error(response.message || 'Error!')
    }
  } catch (error) {
    return new Error(error.message) // 에러 발생 시 null 반환
  }
}
