// React, Next
import { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'

// Materials
import { Autocomplete, TextField } from '@mui/material'

// Custom Components

// Utils
import { fetchAddressesLevel2 } from '@/store/slices/addressSlice'

const AddressL2 = ({
  value,
  onChange,
  sx,
  readOnly = false,
  label = 'Address Level 2',
}) => {
  const dispatch = useDispatch()
  const { addressL2Options, L2status, L2error, addressL1 } = useSelector(
    (state) => state.address
  )

  useEffect(() => {
    dispatch(fetchAddressesLevel2(addressL1))
  }, [addressL1, dispatch])

  const handleAddressChange = (event, value) => {
    onChange(value ? value.addressLevel2Id : '')
  }
  return (
    <Autocomplete
      value={
        addressL2Options
          ? addressL2Options.find(
              (option) => option.addressLevel2Id === value
            ) || null
          : null
      }
      options={addressL2Options || []}
      getOptionLabel={(addressL2Options) =>
        addressL2Options.addressLevel2 || ''
      }
      onChange={handleAddressChange}
      renderInput={(params) => (
        <TextField
          {...params}
          label={label}
          InputProps={{
            ...params.InputProps,
            readOnly: readOnly,
          }}
        />
      )}
      disabled={readOnly}
      sx={sx}
    />
  )
}

AddressL2.displayName = 'AddressL2'

export default AddressL2
