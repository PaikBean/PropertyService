export const fetchSearchBuildngs = async (data) => {
  try {
    const url = new URL(
      '/api/building/v1/building-list',
      window.location.origin
    )
    const params = {
      ownerPhoneNumber: data.ownerPhoneNumber,
      addressLevel1Id: data.addressLevel1Id,
      addressLevel2Id: data.addressLevel2Id,
    }

    Object.keys(params).forEach((key) => {
      if (params[key] !== null && params[key] !== '') {
        url.searchParams.append(key, params[key])
      }
    })

    const response = await fetch(url, {
      method: 'GET',
    })

    if (!response.ok) {
      throw new Error(response.statusText)
    }

    const result = await response.json()

    if (result.responseCode === 'FAIL') {
      throw new Error(result.message || 'Failed to fetch data')
    }

    return result
  } catch (error) {
    console.error('Error saving revenue data:', error)
    throw error
  }
}
