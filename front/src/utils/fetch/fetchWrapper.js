const handleResponse = async (response) => {
  const result = await response.json()
  if (!response.ok || result.responseCode === 'FAIL') {
    throw new Error(result.message || response.statusText)
  }
  return result
}

export const fetchGet = async (url, pathParams = {}, queryParams = {}) => {
  try {
    let path = url
    for (const [key, value] of Object.entries(pathParams)) {
      path = path.replace(`:${key}`, value)
    }

    const queryString = new URLSearchParams(queryParams).toString()
    const finalUrl = queryString ? `${path}?${queryString}` : path

    const response = await fetch(finalUrl, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
    })

    return await handleResponse(response)
  } catch (error) {
    console.error('Error fetching data:', error)
    throw error
  }
}
/*  사용 예시

const getBuildingData = async () => {
  try {
    const pathParams = { id: 123 };
    const queryParams = { filter: 'active' };
    const data = await fetchGet('/api/building/v1/building/:id', pathParams, queryParams);
    console.log('Fetched data:', data);
  } catch (error) {
    console.error('Error fetching building data:', error);
  }
};

getBuildingData();

*/

export const fetchPost = async (url, data) => {
  try {
    const response = await fetch(url, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(data),
    })

    return await handleResponse(response)
  } catch (error) {
    console.error('Error posting data:', error)
    throw error
  }
}
/*  사용 예시

const createBuilding = async () => {
  try {
    const data = {
      ownerName: 'John Doe',
      ownerRelation: 'Owner',
      ownerPhoneNumber: '123-456-7890',
      buildingAddressLevel1: '123 Main St',
      buildingAddressLevel2: 'Suite 100',
      buildingAddressLevel3: 'Building A',
      buildingRemark: 'New building',
    };
    const response = await fetchPost('/api/building/v1/building', data);
    console.log('Building created:', response);
  } catch (error) {
    console.error('Error creating building:', error);
  }
};
*/

export const fetchPut = async (url, data) => {
  try {
    const response = await fetch(url, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(data),
    })

    return await handleResponse(response)
  } catch (error) {
    console.error('Error putting data:', error)
    throw error
  }
}
/*  사용 예시

const updateBuilding = async () => {
  try {
    const data = {
      ownerName: 'Jane Doe',
      ownerRelation: 'Owner',
      ownerPhoneNumber: '987-654-3210',
      buildingAddressLevel1: '123 Main St',
      buildingAddressLevel2: 'Suite 200',
      buildingAddressLevel3: 'Building B',
      buildingRemark: 'Updated building',
    };
    const response = await fetchPut('/api/building/v1/building', data);
    console.log('Building updated:', response);
  } catch (error) {
    console.error('Error updating building:', error);
  }
};

updateBuilding();
*/

export const fetchDelete = async (url, pathParams = {}) => {
  try {
    let path = url
    for (const [key, value] of Object.entries(pathParams)) {
      path = path.replace(`:${key}`, value)
    }

    const response = await fetch(path, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json',
      },
    })

    return await handleResponse(response)
  } catch (error) {
    console.error('Error deleting data:', error)
    throw error
  }
}
/*  사용 예시

const deleteBuilding = async () => {
  try {
    const pathParams = { id: 123 };
    const response = await fetchDelete('/api/building/v1/building/:id', pathParams);
    console.log('Building deleted:', response);
  } catch (error) {
    console.error('Error deleting building:', error);
  }
};

deleteBuilding();
*/
