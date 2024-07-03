export const fetchRegistBuilding = async (data) => {
  try {
    const requestData = {
      ownerName: data.ownerName,
      ownerRelation: data.ownerRelation,
      ownerPhoneNumber: data.ownerPhoneNumber,
      buildingAddressLevel1: data.buildingAddressLevel1,
      buildingAddressLevel2: data.buildingAddressLevel2,
      buildingAddressLevel3: data.buildingAddressLevel3,
      buildingRemark: data.buildingRemark,
    }
    const response = await fetch(`/api/building/v1/building`, {
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
