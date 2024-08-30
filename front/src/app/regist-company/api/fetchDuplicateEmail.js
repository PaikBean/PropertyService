import { fetchGet } from '@/utils/fetch/fetchWrapper'

export const fetchDuplicateEmail = async (data) => {
  try {
    const queryParams = {
      email: data,
    }
    const response = await fetchGet(
      '/api/manager/v1/duplicate',
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
