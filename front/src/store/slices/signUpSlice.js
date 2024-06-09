// src/store/slices/signupSlice.js
import { createAsyncThunk, createSlice } from '@reduxjs/toolkit'

// 비동기 액션 크리에이터 정의
export const fetchSignUp = createAsyncThunk(
  'signup/fetchSignUp',
  async (managerForm, { rejectWithValue }) => {
    try {
      const requestBody = {
        companyCode: managerForm.companyCode,
        managerName: managerForm.basicInfo.managerName,
        managerPhoneNumber: managerForm.basicInfo.managerPhoneNumber,
        managerAddressLevel1: managerForm.basicInfo.managerAddressLevel1,
        managerAddressLevel2: managerForm.basicInfo.managerAddressLevel2,
        managerAddressLevel3: managerForm.basicInfo.managerAddressLevel3,
        gender: managerForm.basicInfo.gender,
        departmentName: managerForm.detailInfo.departmentId,
        managerRank: managerForm.detailInfo.managerRank,
        managerPosition: managerForm.detailInfo.managerPosition,
        state: managerForm.detailInfo.state,
        managerCode: managerForm.detailInfo.managerCode,
        managerEmail: managerForm.loginInfo.managerEmail,
        managerPassword: managerForm.loginInfo.managerPassword,
      }

      const response = await fetch('/api/manager/v1/sign-up', {
        // 실제 API 엔드포인트로 변경하세요.
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(requestBody),
      })
      if (response.responseCode === 'FAIL') {
        throw new Error('Error!')
      }
      return await response.json()
    } catch (error) {
      return rejectWithValue(error.message)
    }
  }
)

export const fetchSignUpApiCallTest = createAsyncThunk(
  'signup/fetchSignUpApiCallTest',
  async (managerForm, { rejectWithValue }) => {
    try {
      const requestBody = {
        companyCode: 'COMP001',
        managerName: 'test',
        managerPhoneNumber: '010-0000-0000',
        managerAddressLevel1: 1,
        managerAddressLevel2: 1,
        managerAddressLevel3: '주소 레벨 3',
        gender: 'MALE',
        departmentName: 'Sales',
        managerRank: '대표',
        managerPosition: '사업본부',
        state: '재직',
        managerCode: '사원번호',
        managerEmail: 'example@example.com',
        managerPassword: 'password',
      }

      const response = await fetch('/api/manager/v1/sign-up', {
        // 실제 API 엔드포인트로 변경하세요.
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(requestBody),
      })
      if (response.responseCode === 'FAIL') {
        throw new Error('Error!')
      }
      console.log(response)
      return await response.json()
    } catch (error) {
      return rejectWithValue(error.message)
    }
  }
)

export const fetchDuplicateEmail = createAsyncThunk(
  'signup/fetchDuplicateEmail',
  async (email, { rejectWithValue }) => {
    try {
      const response = await fetch(`/api/manager/v1/duplicate?email=${email}`, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
        },
      })
      const data = await response.json()
      if (response.ok) {
        return data
      } else {
        throw new Error(data.message || 'Error!')
      }
    } catch (error) {
      return rejectWithValue(error.message)
    }
  }
)

const signUpSlice = createSlice({
  name: 'signup',
  initialState: {
    companyCode: null,
    basicInfo: {
      managerName: '',
      managerPhoneNumber: '',
      managerAddressLevel1: '',
      managerAddressLevel2: '',
      managerAddressLevel3: '',
      gender: '',
    },
    detailInfo: {
      departmentId: '',
      managerRank: '',
      managerPosition: '',
      state: '',
      managerCode: '',
    },
    loginInfo: {
      managerEmail: '',
      duplicateEmail: false,
      managerPassword: '',
      checkPassword: '',
      checkResult: false,
    },
  },
  reducers: {
    setCompanyInfo: (state, action) => {
      state.companyCode = action.payload
    },
    setBasicInfo: (state, action) => {
      state.basicInfo = {
        ...state.basicInfo,
        ...action.payload,
      }
    },
    setDetailInfo: (state, action) => {
      state.detailInfo = {
        ...state.detailInfo,
        ...action.payload,
      }
    },
    setLoginInfo: (state, action) => {
      state.loginInfo = {
        ...state.loginInfo,
        ...action.payload,
      }
    },
    clearSignup: (state) => {
      state.company = null
      state.basicInfo = {
        managerName: '',
        managerPhoneNumber: '',
        managerAddressLevel1: '',
        managerAddressLevel2: '',
        managerAddressLevel3: '',
        gender: '',
      }
      state.detailInfo = {
        departmentName: '',
        managerRank: '',
        managerPosition: '',
        state: '',
        managerCode: '',
      }
      state.loginInfo = {
        managerEmail: '',
        managerPassword: '',
        checkPassword: '',
        checkResult: false,
      }
    },
  },
})

export const { setCompanyInfo, setBasicInfo, setDetailInfo, setLoginInfo } =
  signUpSlice.actions
export default signUpSlice.reducer
