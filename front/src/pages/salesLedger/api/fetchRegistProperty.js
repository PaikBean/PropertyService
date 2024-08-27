import { fetchPost } from '@/utils/fetch/fetchWrapper'

export const fetchRegistProperty = async (registData) => {
  try {
    const result = await fetchPost(
      '/api/property/v1/property',
      registData
    )
    if (result.responseCode === 'SUCCESS') {
      return result
    } else {
      throw new Error(result.message)
    }
  } catch (error) {
    console.error('Error saving revenue data:', error)
    throw error
  }
}
