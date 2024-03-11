import { SnackbarProvider, useSnackbar } from 'notistack';
import Page from './Page';
import React from 'react';
import ReactDOM from 'react-dom';


export default function App() {
    return (
        <div>
            <SnackbarProvider maxSnack={3} preventDuplicate>
                <HookCaller />
            </SnackbarProvider>
        </div>
    );
}

export const HookCaller = () => {
    const { enqueueSnackbar } = useSnackbar();

    function showMessage(message, variant = "info") {
        enqueueSnackbar(message, { variant: variant })
    }

    return <Page showMessage={showMessage} />;
};