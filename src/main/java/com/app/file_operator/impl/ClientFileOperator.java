package com.app.file_operator.impl;

import com.app.model.client.Client;

public class ClientFileOperator extends AbstractFileOperator<Client> {

    @Override
    Client parse(String line) {
        return Client.parse(line);
    }
}
