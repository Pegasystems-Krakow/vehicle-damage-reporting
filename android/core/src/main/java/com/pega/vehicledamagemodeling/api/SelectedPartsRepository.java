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

import com.google.gson.JsonObject;
import java.util.ArrayList;

public class SelectedPartsRepository{

    private ArrayList<String> parts;
    private JsonObject initJson;
    private Parser parser;
    private PartRoot partRoot;

    public SelectedPartsRepository(JsonObject initJson, Parser parser){
        this.parser = parser;
        this.initJson = initJson;
        this.partRoot = this.parser.parseToPartRoot(initJson);
        this.parts = partRoot.getParts();
    }

    public void add(String part) {
        if( !parts.contains(part)){
            parts.add(part);
        }
    }

    public boolean remove(String part) { return parts.remove(part); }

    public ArrayList<String> getAll() {
        return parts;
    }

    public JsonObject getJson(){
        return parser.parseToJson(new PartRoot(parts,partRoot.getMainScreenText()));
    }

    public String getMainScreenText(){
        return partRoot.getMainScreenText();
    }

    public JsonObject getInitJson() {
        return initJson;
    }
}
