export const fetchSearchClient = async (clientId) => {
  try {
    const url = new URL('/api/client/v1/client-detail', window.location.origin)
    const params = {
      clientId: clientId,
    }

    Object.keys(params).forEach((key) => {
      if (params[key] !== null && params[key] !== '') {
        url.searchParams.append(key, params[key])
      }
    })

    console.log(url.toString()) // URL을 로그로 출력하여 확인
    const response = await fetch(url, {
      method: 'GET',
    })
    if (!response.ok) {
      // throw new Error('Network response was not ok')
      throw new Error(response.statusText)
    }
    const result = await response.json()
    return result
  } catch (error) {
    console.error('Error saving revenue data:', error)
    throw error
  }
}
