import { fetchGet } from '@/utils/fetch/fetchWrapper'

const { createSlice, createAsyncThunk } = require('@reduxjs/toolkit')

export const fetchDepartmentList = createAsyncThunk(
  'manager/fetchDepartmentList',
  async (companyCode) => {
    try {
      const queryParams = {
        companyCode: companyCode,
      }
      const response = await fetchGet(
        '/api/department/v1/department-list',
        {},
        queryParams
      )

      if (response.responseCode === 'SUCCESS') {
        return response.data
      } else {
        throw new Error(response.message || 'Error!')
      }
    } catch (error) {
      return new Error(error.message) // 에러 발생 시 null 반환
    }
  }
)

const departmentSlice = createSlice({
  name: 'department2',
  initialState: {
    options: null,
    status: 'idle',
    error: null,
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchDepartmentList.pending, (state) => {
        state.status = 'loading'
      })
      .addCase(fetchDepartmentList.fulfilled, (state, action) => {
        state.status = 'succeeded'
        state.options = action.payload
      })
      .addCase(fetchDepartmentList.rejected, (state, action) => {
        state.status = 'failed'
        state.error = action.error.message
      })
  },
})

export const {} = departmentSlice.actions

export default departmentSlice.reducer
