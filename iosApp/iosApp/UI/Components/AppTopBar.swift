//
//  AppTopBar.swift
//  iosApp
//
//  Created by Suraj GP on 07/05/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI

struct AppTopBar: View {
    
    let navigateUpIcon: String
    let title: String
    
    var onNavigateUpClicked: () -> Void = {}
    
    var body: some View {
        
        ZStack(alignment: .leading) {
            
            Button(action: {
                self.onNavigateUpClicked()
            }) {
                Image(systemName: self.navigateUpIcon)
                    .font(.system(size: 24, weight: .medium))
                    .foregroundColor(Color.Neomorph.text)
            }
            .padding()
            .neomorph(isCircle: true)
            
            HStack {
                Spacer()
                
                Text(self.title)
                    .font(.title)
                    .fontWeight(.bold)
                    .foregroundColor(Color.Neomorph.text)
                
                Spacer()
            }
        }
    }
}

struct AppTopBarPreview: PreviewProvider {
    static var previews: some View {
        
        ZStack(alignment: .top) {
            Color.Neomorph.main
                .edgesIgnoringSafeArea(.all)
            
            AppTopBar(navigateUpIcon: "arrow.backward", title: "Title")
                .padding()
        }
    }
}
