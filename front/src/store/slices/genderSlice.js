import { fetchGet } from '@/utils/fetch/fetchWrapper'
import { createAsyncThunk, createSlice } from '@reduxjs/toolkit'

export const fetchGenderList = createAsyncThunk(
  'common/gender-list',
  async () => {
    try {
      const response = await fetchGet('/api/common/v1/gender-list', {}, null)
      if (response.responseCode === 'SUCCESS') {
        return response.data // response.data를 반환
      } else {
        throw new Error(response.message || 'Error!')
      }
    } catch (error) {
      throw new Error(error.message)
    }
  }
)

const genderSlice = createSlice({
  name: 'gender',
  initialState: {
    options: [],
    status: 'idle',
    error: null,
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchGenderList.pending, (state) => {
        state.status = 'loading'
      })
      .addCase(fetchGenderList.fulfilled, (state, action) => {
        state.status = 'succeeded'
        state.options = action.payload
      })
      .addCase(fetchGenderList.rejected, (state, action) => {
        state.status = 'failed'
        state.error = action.error.message
      })
  },
})

export default genderSlice.reducer
