import { fetchGet } from '@/utils/fetch/fetchWrapper'
import { createAsyncThunk, createSlice } from '@reduxjs/toolkit'

export const fetchManagerStateList = createAsyncThunk(
  'common/manager-state-list',
  async () => {
    try {
      const response = await fetchGet(
        '/api/common/v1/manager-state-list',
        {},
        null
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

const managerStateSlice = createSlice({
  name: 'managerState',
  initialState: {
    options: [],
    status: 'idle',
    error: null,
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchManagerStateList.pending, (state) => {
        state.status = 'loading'
      })
      .addCase(fetchManagerStateList.fulfilled, (state, action) => {
        state.status = 'succeeded'
        state.options = action.payload.data
      })
      .addCase(fetchManagerStateList.rejected, (state, action) => {
        state.status = 'failed'
        state.error = action.error.message
      })
  },
})

export default managerStateSlice.reducer
