import { validateDate } from '@mui/x-date-pickers/internals'
import companySlice from './companySlice'

const { createSlice, createAsyncThunk } = require('@reduxjs/toolkit')

export const fetchValidBizNumber = createAsyncThunk(
  'signup/fetchValidBizNumber',
  async (validData, { rejectWithValue }) => {
    try {
      // console.log(validData)
      // const response = await fetch(
      //   `/api/company/v1/validate/biz-number?
      //     bNo=${validData.bizNumber}&startDate=${validData.bizStartDate}
      //     &pName=${validData.presidentName}`,
      //   {
      //     method: 'GET',
      //     headers: {
      //       'Content-Type': 'application/json',
      //     },
      //   }
      // )
      // const data = await response.json()
      const data = {
        responseCode: 'SUCCESS',
        data: null,
        message: null,
        code: '200',
      }
      if (data.responseCode === 'SUCCESS') {
        console.log(data)
        return data
      } else {
        throw new Error(data.message || 'Error!')
      }
    } catch (error) {
      return rejectWithValue(error.message)
    }
  }
)

export const fetchRegistCompany = createAsyncThunk(
  'signup/fetchRegistCompany',
  async (registData, { rejectWithValue }) => {
    try {
      //   const response = await fetch(
      //     `/api/common/v1/regist-company
      //     {
      //       method: 'POST',
      //       headers: {
      //         'Content-Type': 'application/json',
      //       },
      // body: JSON.stringify(requestBody),
      //     }
      //   )
      // const data = await response.json()
      const data = {
        responseCode: 'SUCCESS',
        data: {
          companyCode: 'COMP001',
        },
        message: null,
        code: '200',
      }
      if (data.responseCode === 'SUCCESS') {
        return data
      } else {
        throw new Error(data.message || 'Error!')
      }
    } catch (error) {
      return rejectWithValue(error.message)
    }
  }
)

const registCompanySlice = createSlice({
  name: 'registCompany',
  initialState: {
    validateData: {
      bizNumber: null,
      bizStartDate: '',
      presidentName: '',
      validateResult: false,
      status: 'idle',
      error: null,
    },
    registData: {
      bizNumber: '',
      presidentName: '',
      companyName: '',
      addressLevel1: null,
      addressLevel2: null,
      addressLevel3: '',
      companyEmaill: '',
    },
    companycode: '',
    status: 'idle',
    error: null,
  },
  reducers: {
    setRegistData: (state, action) => {
      state.registData = action.payload
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchValidBizNumber.pending, (state) => {
        state.validateData.status = 'loading'
      })
      .addCase(fetchValidBizNumber.fulfilled, (state, action) => {
        console.log(action.payload)
        state.validateData.status = 'succeeded'
        if (action.payload.code === '200') {
          state.validateData.validateResult = true
          state.registData.bizNumber = state.validateData.bizNumber
          state.registData.bizStartDate = state.validateData.bizStartDate
          state.registData.presidentName = state.validateData.presidentName
        } else {
          state.validateData.validateResult = false
        }
      })
      .addCase(fetchValidBizNumber.rejected, (state, action) => {
        state.validateData.status = 'failed'
        state.validateData.error = action.error.message
      })

      .addCase(fetchRegistCompany.pending, (state) => {
        state.status = 'loading'
      })
      .addCase(fetchRegistCompany.fulfilled, (state, action) => {
        console.log(action.payload)
        state.status = 'succeeded'
        state.companycode = action.payload.data.companyCode
      })
      .addCase(fetchRegistCompany.rejected, (state, action) => {
        state.status = 'failed'
        state.error = action.error.message
      })
  },
})

export const { setRegistData } = registCompanySlice.actions

export default registCompanySlice.reducer
