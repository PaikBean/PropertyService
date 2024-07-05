const { createSlice, createAsyncThunk } = require('@reduxjs/toolkit')

export const fetchPropertyTypeList = createAsyncThunk(
  'common/fetchPropertyTypeList',
  async () => {
    // const response = await fetch('/api/schedule/v1/schedule-type-list')
    // if (!response.ok) {
    //   // throw new Error('Network response was not ok')
    //   throw new Error(response.statusText)
    // }
    // const result = await response.json()
    const result = {
      data: [
        {
          propertyTypeId: '1',
          propertyType: '상업용',
        },
        {
          propertyTypeId: '2',
          propertyType: '주거용',
        },
      ],
    }
    return result
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
