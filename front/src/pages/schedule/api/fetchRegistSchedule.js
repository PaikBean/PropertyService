import { fetchPost } from '@/utils/fetch/fetchWrapper'

export const fetchRegistSchedule = async (data) => {
  try {
    const result = await fetchPost(
      '/api/schedule/v1/schedule',
      data
    )
    if (result.responseCode === 'SUCCESS') {
      return result
    } else {
      throw new Error(result.message)
    }
  } catch (error) {
    console.error('Error saving data:', error)
    throw error
  }
}