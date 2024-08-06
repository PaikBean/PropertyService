const getAuthHeader = () => {
  const token = localStorage.getItem('token')
  // return token ? { Authorization: `Bearer ${token}` } : {}
  return token ? { Authorization: `${token}` } : {}
}

const handleResponse = async (response) => {
  const result = await response.json()
  if (!response.ok || response.status !== 200) {
    throw new Error(result.message || response.statusText)
  }
  return result
}

export const fetchGet = async (url, pathParams = {}, queryParams = {}) => {
  try {
    let path = url;
    for (const [key, value] of Object.entries(pathParams)) {
      path = path.replace(`:${key}`, value);
    }

    const filteredQueryParams = Object.entries(queryParams).reduce((acc, [key, value]) => {
      if (value !== null && value !== '') {
        acc[key] = value;
      }
      return acc;
    }, {});

    const queryString = new URLSearchParams(filteredQueryParams).toString();
    const finalUrl = queryString ? `${path}?${queryString}` : path;

    const headers = {
      'Content-Type': 'application/json',
      ...getAuthHeader(),
    };

    const response = await fetch(finalUrl, {
      method: 'GET',
      headers,
    });

    return await handleResponse(response);
  } catch (error) {
    console.error('Error fetching data:', error);
    throw error;
  }
};

export const fetchPost = async (url, data) => {
  try {
    const headers = {
      'Content-Type': 'application/json',
      ...getAuthHeader(),
    }

    const response = await fetch(url, {
      method: 'POST',
      headers,
      body: JSON.stringify(data),
    })

    return await handleResponse(response)
  } catch (error) {
    console.error('Error posting data:', error)
    throw error
  }
}

export const fetchPut = async (url, data) => {
  try {
    const headers = {
      'Content-Type': 'application/json',
      ...getAuthHeader(),
    }

    const response = await fetch(url, {
      method: 'PUT',
      headers,
      body: JSON.stringify(data),
    })

    return await handleResponse(response)
  } catch (error) {
    console.error('Error putting data:', error)
    throw error
  }
}

export const fetchDelete = async (url, pathParams = {}) => {
  try {
    let path = url
    for (const [key, value] of Object.entries(pathParams)) {
      path = path.replace(`:${key}`, value)
    }

    const headers = {
      'Content-Type': 'application/json',
      ...getAuthHeader(),
    }

    const response = await fetch(path, {
      method: 'DELETE',
      headers,
    })

    return await handleResponse(response)
  } catch (error) {
    console.error('Error deleting data:', error)
    throw error
  }
}

/** API CALL  공통 코드 사용 예시**/

/** GET 요청 사용 예시 **/
// // 이 함수는 특정 ID를 가진 건물의 정보를 가져오는 API를 호출합니다.
// // pathParams로 ID를 동적으로 대체하고, 필요한 경우 추가적인 쿼리 파라미터를 전송합니다.
// const getBuildingData = async () => {
//   try {
//     const pathParams = { id: 123 };  // 경로에 포함될 파라미터
//     const queryParams = { filter: 'active' };  // 쿼리 파라미터
//     const data = await fetchGet('/api/building/v1/building/:id', pathParams, queryParams);
//     console.log('Fetched data:', data);  // 성공적으로 데이터를 받아온 경우 출력
//   } catch (error) {
//     console.error('Error fetching building data:', error);  // 에러 발생시 콘솔에 에러 출력
//   }
// };

/** POST 요청 사용 예시 **/
// // 이 함수는 새로운 건물 정보를 생성하는 API를 호출합니다.
// // 사용자 입력을 바탕으로 요청 본문을 구성하고 서버에 데이터를 전송합니다.
// const createBuilding = async () => {
//   try {
//     const data = {
//       ownerName: 'John Doe',  // 건물 소유주 이름
//       ownerRelation: 'Owner',  // 소유주와의 관계
//       ownerPhoneNumber: '123-456-7890',  // 소유주 전화번호
//       buildingAddressLevel1: '123 Main St',  // 주소 1
//       buildingAddressLevel2: 'Suite 100',  // 주소 2
//       buildingAddressLevel3: 'Building A',  // 주소 3
//       buildingRemark: 'New building'  // 건물에 대한 비고
//     };
//     const response = await fetchPost('/api/building/v1/building', data);
//     console.log('Building created:', response);  // 건물 생성에 성공했을 경우 출력
//   } catch (error) {
//     console.error('Error creating building:', error);  // 에러 발생시 콘솔에 에러 출력
//   }
// };

/** PUT 요청 사용 예시 **/
// // 이 함수는 기존 건물 정보를 업데이트하는 API를 호출합니다.
// // 변경된 데이터를 서버에 전송하여 기존의 데이터를 업데이트합니다.
// const updateBuilding = async () => {
//   try {
//     const data = {
//       ownerName: 'Jane Doe',  // 변경될 건물 소유주 이름
//       ownerRelation: 'Owner',  // 소유주와의 관계
//       ownerPhoneNumber: '987-654-3210',  // 변경될 소유주 전화번호
//       buildingAddressLevel1: '123 Main St',  // 주소 1
//       buildingAddressLevel2: 'Suite 200',  // 변경될 주소 2
//       buildingAddressLevel3: 'Building B',  // 변경될 주소 3
//       buildingRemark: 'Updated building'  // 변경된 건물에 대한 비고
//     };
//     const response = await fetchPut('/api/building/v1/building', data);
//     console.log('Building updated:', response);  // 건물 정보 업데이트에 성공했을 경우 출력
//   } catch (error) {
//     console.error('Error updating building:', error);  // 에러 발생시 콘솔에 에러 출력
//   }
// };

/** DELETE 요청 사용 예시 **/
// // 이 함수는 특정 ID를 가진 건물을 삭제하는 API를 호출합니다.
// // pathParams를 사용하여 API 경로에 포함된 동적 파라미터를 대체합니다.
// const deleteBuilding = async () => {
//   try {
//     const pathParams = { id: 123 };  // 삭제할 건물의 ID
//     const response = await fetchDelete('/api/building/v1/building/:id', pathParams);
//     console.log('Building deleted:', response);  // 건물 삭제에 성공했을 경우 출력
//   } catch (error) {
//     console.error('Error deleting building:', error);  // 에러 발생시 콘솔에 에러 출력
//   }
// };
