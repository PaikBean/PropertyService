import { createSlice } from '@reduxjs/toolkit'

const exampleSlice = createSlice({
  name: 'example',
  initialState: {
    value: 0,
  },
  reducers: {
    increment: (state) => {
      state.value += 1
    },
    decrement: (state) => {
      state.value -= 1
    },
    제곱: (state) => {
      state.value *= state.value
    },
    초기화: (state) => {
      state.value = 0
    },
    alert표시: (state) => {
      alert(state.value)
    },
  },
})

export const { increment, decrement, 제곱, 초기화, alert표시 } =
  exampleSlice.actions
export default exampleSlice.reducer
