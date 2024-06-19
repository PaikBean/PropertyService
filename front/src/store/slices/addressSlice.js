import { createSlice, createAsyncThunk } from '@reduxjs/toolkit'

export const fetchAddressesLevel1 = createAsyncThunk(
  'addresses/fetchAddressesLevel1',
  async () => {
    const response = await fetch('/api/common/v1/address-level1-list')
    const data = await response.json()
    return data
  }
)

export const fetchAddressesLevel2 = createAsyncThunk(
  'addresses/fetchAddressesLevel2',
  async (addressLevel1) => {
    const response = await fetch(
      `/api/common/v1/address-level2-list/${addressLevel1}`
    )
    const data = await response.json()
    return data
  }
)

const addressSlice = createSlice({
  name: 'address',
  initialState: {
    addressL1Options: null,
    addressL2Options: null,
    addressL1: null,
    addressL2: null,
    L1status: 'idle',
    L2status: 'idle',
    L1error: null,
    L2error: null,
  },
  reducers: {
    setAddressL1: (state, action) => {
      state.addressL1 = action.payload
    },
    setAddressL2: (state, action) => {
      state.addressL2 = action.payload
    },
    clearAddress: (state, action) => {
      state.addressL2 = null
      state.addressL1 = null
    },
  },
  extraReducers: (builder) => {
    builder
      // Address Level 1
      .addCase(fetchAddressesLevel1.pending, (state) => {
        state.L1status = 'loading'
      })
      .addCase(fetchAddressesLevel1.fulfilled, (state, action) => {
        state.L1status = 'succeeded'
        state.addressL1Options = action.payload.data
      })
      .addCase(fetchAddressesLevel1.rejected, (state, action) => {
        state.L1status = 'failed'
        state.L1error = action.error.message
      })
      // Address Level 2
      .addCase(fetchAddressesLevel2.pending, (state) => {
        state.L2status = 'loading'
      })
      .addCase(fetchAddressesLevel2.fulfilled, (state, action) => {
        state.L2status = 'succeeded'
        state.addressL2Options = action.payload.data
      })
      .addCase(fetchAddressesLevel2.rejected, (state, action) => {
        state.L2status = 'failed'
        state.L2error = action.error.message
      })
  },
})

export const { setAddressL1, setAddressL2, clearAddress } = addressSlice.actions

export default addressSlice.reducer
