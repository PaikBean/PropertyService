const { createSlice, createAsyncThunk } = require('@reduxjs/toolkit')

export const fetchPropertyTypeList = createAsyncThunk(
  'common/fetchPropertyTypeList',
  async () => {
    try {
      const response = await fetchGet(
        '/api/property/v1/property-type-list'
      )
      console.log(response)
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

const propertyTypeSlice = createSlice({
  name: 'scheduleType',
  initialState: {
    options: null,
    status: 'idle',
    error: null,
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchPropertyTypeList.pending, (state) => {
        state.status = 'loading'
      })
      .addCase(fetchPropertyTypeList.fulfilled, (state, action) => {
        state.status = 'succeeded'
        state.options = action.payload.data
      })
      .addCase(fetchPropertyTypeList.rejected, (state, action) => {
        state.status = 'failed'
        state.error = action.error.message
      })
  },
})

export default propertyTypeSlice.reducer
