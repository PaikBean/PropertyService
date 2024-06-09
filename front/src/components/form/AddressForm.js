import { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { Autocomplete, Grid, Stack, TextField, Typography } from '@mui/material'
import {
  clearAddressL2Option,
  fetchAddressesLevel1,
  fetchAddressesLevel2,
  setAddressL1,
  setAddressL2,
} from 'front/src/store/slices/addressSlice'

const AddressForm = () => {
  const dispatch = useDispatch()
  const {
    addressL1Options,
    addressL2Options,
    addressL1,
    addressL2,
    addressL3,
    L1status,
    L2status,
  } = useSelector((state) => state.address)

  useEffect(() => {
    if (L1status === 'idle') {
      dispatch(fetchAddressesLevel1())
    }
  }, [L1status, dispatch]) // 종속성 배열 추가

  useEffect(() => {
    // dispatch(clearAddressL2Option())
    dispatch(fetchAddressesLevel2(addressL1))
  }, [addressL1, dispatch])

  return (
    // <Grid item>
    //   <Grid item>
    //     <Autocomplete
    //       options={addressL1Options || []}
    //       getOptionLabel={(addressL1Options) =>
    //         addressL1Options.addressLevel1 || ''
    //       }
    //       onChange={(event, value) => {
    //         dispatch(setAddressL1(value ? value.addressLevel1Id : ''))
    //       }}
    //       renderInput={(params) => (
    //         <TextField {...params} label="Address Level 1" />
    //       )}
    //     />
    //   </Grid>
    //   <Grid>
    //     <Autocomplete
    //       options={addressL2Options || []}
    //       getOptionLabel={(addressL2Options) =>
    //         addressL2Options.addressLevel2 || ''
    //       }
    //       onChange={(event, value) => {
    //         dispatch(setAddressL2(value ? value.addressLevel2Id : ''))
    //       }}
    //       renderInput={(params) => (
    //         <TextField {...params} label="Address Level 2" />
    //       )}
    //     />
    //   </Grid>
    // </Grid>
    <Stack gap={2}>
      <Autocomplete
        options={addressL1Options || []}
        getOptionLabel={(addressL1Options) =>
          addressL1Options.addressLevel1 || ''
        }
        onChange={(event, value) => {
          dispatch(setAddressL1(value ? value.addressLevel1Id : ''))
        }}
        renderInput={(params) => (
          <TextField {...params} label="Address Level 1" />
        )}
      />
      <Autocomplete
        options={addressL2Options || []}
        getOptionLabel={(addressL2Options) =>
          addressL2Options.addressLevel2 || ''
        }
        onChange={(event, value) => {
          dispatch(setAddressL2(value ? value.addressLevel2Id : ''))
        }}
        renderInput={(params) => (
          <TextField {...params} label="Address Level 2" />
        )}
      />
    </Stack>
  )
}

AddressForm.displayName = 'AddressForm'

export default AddressForm
