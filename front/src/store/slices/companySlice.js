// src/store/slices/companySlice.js
import { createSlice, createAsyncThunk } from '@reduxjs/toolkit'

export const fetchCompanyDetails = createAsyncThunk(
  'company/fetchCompanyDetails',
  async (companyCode, { rejectWithValue }) => {
    try {
      //   const response = await fetch(`/api/company/${companyCode}`) // API 엔드포인트를 변경하세요
      //   if (!response.ok) {
      //     throw new Error('Company not found')
      //   }
      //   const data = await response.json()
      const data = {
        companyCode: 'COMP001',
        companyName: 'Temp Company',
        companyBizNumber: '123-123456',
        companyPresidentName: '김덕수',
      }
      return data
    } catch (error) {
      return rejectWithValue(error.message)
    }
  }
)

const companySlice = createSlice({
  name: 'companyInfo',
  initialState: {
    companyInfo: null,
    status: 'idle',
    error: null,
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchCompanyDetails.pending, (state) => {
        state.status = 'loading'
      })
      .addCase(fetchCompanyDetails.fulfilled, (state, action) => {
        state.status = 'succeeded'
        state.companyInfo = action.payload
      })
      .addCase(fetchCompanyDetails.rejected, (state, action) => {
        state.status = 'failed'
        state.error = action.payload
      })
  },
})

export default companySlice.reducer
