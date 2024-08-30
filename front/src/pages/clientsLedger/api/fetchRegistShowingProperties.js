import { fetchPost } from '@/utils/fetch/fetchWrapper'

export const fetchRegistShowingProperties = async (clientId, showingPropertyList) => {
  try {
    const requestData = {
        clientId: clientId,
        showingPropertyList: showingPropertyList
    }
    const result = await fetchPost(
      '/api/property/v1/showing-property-list',
      requestData
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
