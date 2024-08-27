import { fetchGet } from '@/utils/fetch/fetchWrapper'

const { createSlice, createAsyncThunk } = require('@reduxjs/toolkit')

export const fetchPropertyTypeList = createAsyncThunk(
  'common/fetchPropertyTypeList',
  async () => {
    const response = await fetch('/api/property/v1/property-type-list')
    const data = await response.json()
    return data
  }
)

const propertyTypeSlice = createSlice({
  name: 'propertyType',
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
