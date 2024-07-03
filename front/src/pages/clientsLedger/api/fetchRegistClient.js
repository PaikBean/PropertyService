export const fetchRegistClient = async (data) => {
  try {
    const requestData = {
      clientName: data.clientName,
      inflowTypeId: data.inflowTypeId,
      clientPhoneNumber: data.clientPhoneNumber,
      managerId: data.managerId,
      remark: data.remark,
      propertyList: data.propertyList,
    }
    console.log(requestData)
    const response = await fetch(`/api/client/v1/client`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(requestData),
    })
    console.log(response)

    const result = await response.json()
    if (!response.ok || result.responseCode === 'FAIL') {
      throw new Error(result.message || response.statusText)
    }

    return result
  } catch (error) {
    console.error('Error saving revenue data:', error)
    throw error
  }
}
