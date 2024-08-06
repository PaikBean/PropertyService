import { fetchGet } from '@/utils/fetch/fetchWrapper'
const { createSlice, createAsyncThunk } = require('@reduxjs/toolkit')

export const fetchManagerList = createAsyncThunk(
  'manager/fetchManagerList',
  async () => {
    try {
      const response = await fetchGet(
        '/api/company/v1/manager-list'
      )
      console.log(response)
      if (response.responseCode === 'SUCCESS') {
        return response
      } else {
        throw new Error(response.message || 'Error!')
      }
    } catch (error) {
      return new Error(error.message) // 에러 발생 시 null 반환
    }
  }
)

const managerSlice = createSlice({
  name: 'manager',
  initialState: {
    options: null,
    status: 'idle',
    error: null,
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchManagerList.pending, (state) => {
        state.status = 'loading'
      })
      .addCase(fetchManagerList.fulfilled, (state, action) => {
        state.status = 'succeeded'
        state.options = action.payload.data
      })
      .addCase(fetchManagerList.rejected, (state, action) => {
        state.status = 'failed'
        state.error = action.error.message
      })
  },
})

export const {} = managerSlice.actions

export default managerSlice.reducer
