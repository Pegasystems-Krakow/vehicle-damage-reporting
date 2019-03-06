// Copyright 2019 Flying Vehicle Monster team
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

import Foundation
import SceneKit

internal class DamagedPartsInitializer: DamagedPartsInitializerProtocol {
    
    
    private var nodeHelper: NodeHelperProtocol
    
    init() {
        nodeHelper = NodeHelper()
    }
    
    func initialize(damagedParts: [Selection], carModelNode: SCNNode) {
        
        
        let nodesToHighlight = nodeHelper.getIdsOfSelection(selections: damagedParts)
        updateDamagedPartsOnUI(damagedPartsNames: nodesToHighlight!)
    }
    
    
    private func updateDamagedPartsOnUI(damagedPartsNames: [String]){
        
        for partName in damagedPartsNames{
            let carModelNode = scnScene.rootNode.childNode(withName: CAR_MODEL_NAME, recursively: false)
            let nodeToHighlight = carModelNode?.childNode(withName: partName, recursively: true)
            highlightHandler.setHighlightOn(node: nodeToHighlight!)
        }
    }
}
