// Copyright 2018 Flying Vehicle Monster team
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.pega.vehicledamagemodeling.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.HashSet;

public class Parser {

    private static final String Main_Screen_Text = "mainScreenText";
    private static final String Selection = "selection";
    private static final String Id = "id";

    public String parseToMainScreenText(JsonObject jsonObject){
        JsonElement jsonText = jsonObject.get(Main_Screen_Text);
        String text = "";
        if(jsonText != null){
            text = jsonText.getAsString();
        }
        return text;
    }

    public HashSet<String> parseToSelectedParts(JsonObject jsonObject){
        HashSet<String> selectedParts = new HashSet<>();
        if(jsonObject != null) {
            JsonArray partsArray = jsonObject.getAsJsonArray(Selection);
            if (partsArray != null) {
                for (int i = 0; i < partsArray.size(); i++) {
                    String selection = partsArray.get(i)
                            .getAsJsonObject()
                            .get(Id)
                            .getAsString();
                    selectedParts.add(selection);
                }
            }
        }
        return selectedParts;
    }

    public JsonObject parseToJson(String text, HashSet<String> parts){
        JsonObject parsed = new JsonObject();
        JsonArray parsedArray = new JsonArray();

        for(String s : parts){
            JsonObject jsonProperty = new JsonObject();
            jsonProperty.addProperty(Id, s);
            parsedArray.add(jsonProperty);
        }

        parsed.addProperty(Main_Screen_Text, text);
        parsed.add(Selection,parsedArray);
        return parsed;
    }
}