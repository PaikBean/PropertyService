import { fetchPost } from '@/utils/fetch/fetchWrapper'

export const fetchRegistBuildingRemark = async (data) => {
  try {
    const result = await fetchPost(
      '/api/building/v1/building-remark',
      data
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
